package controller;

import model.*;
import model.Customer.Customer;
import model.Customer.GestorCustomer;
import model.Employee.Employee;
import model.Game.Game;
import view.Menu;
import java.util.Scanner;
import static java.lang.IO.*;

/**
 * Controlador principal del sistema. Orquesta el ciclo de menús,
 * delega la lectura de opciones/datos, y llama a la vista (Menu)
 * y al modelo (GameStore, Employee, Customer, Game) según corresponda.
 * Todas las operaciones contra la base de datos se envuelven en
 * try/catch para no tumbar el programa ante un error de conexión/SQL.
 */
public class Controller {

    private final Scanner scanner;
    private final GameStore store;
    private final Employee employee;

    public Controller(Scanner scanner, GameStore store, Employee employee) {
        this.scanner = scanner;
        this.store = store;
        this.employee = employee;
    }

    public void run() {
        while (true) {
            Menu.printMenuPrincipal();
            int mainOption = leerOpcion();

            switch (mainOption) {
                case 1 -> menuCliente();
                case 2 -> menuEmpleado();
                case 3 -> {
                    println("Sistema cerrado.");
                    return;
                }
                default -> Menu.printOpcionInvalida();
            }
        }
    }

    // ---------------------- MENÚ CLIENTE ----------------------

    private void menuCliente() {
        while (true) {
            Menu.printMenuCliente();
            int customerOption = leerOpcion();

            switch (customerOption) {
                case 1 -> rentarVideojuego();
                case 2 -> devolverVideojuego();
                case 3 -> verVideojuegos();
                case 4 -> {
                    return;
                }
                default -> Menu.printOpcionInvalida();
            }
        }
    }

    private void rentarVideojuego() {
        try {
            println("Ingrese nombre del cliente:");
            String customerName = scanner.nextLine();

            println("Ingrese título del videojuego:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                println("Videojuego no encontrado.");
            } else if (store.isRented(title, customerName)) {
                println("Ese videojuego ya está rentado.");
            } else {
                Customer customer = GestorCustomer.obtenerOCrearCustomer(customerName);
                customer.rentGame(game);
                println("Videojuego rentado: " + game.getTitle());
                println("Precio: " + game.getPrice());
            }
        } catch (Exception e) {
            println("Error al rentar el videojuego: " + e.getMessage());
        }
    }

    private void devolverVideojuego() {
        try {
            println("Ingrese nombre del cliente:");
            String customerName = scanner.nextLine();

            println("Ingrese título del videojuego a devolver:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                println("Videojuego no encontrado.");
            } else if (!store.isRented(title, customerName)) {
                println("Ese videojuego no está rentado por ese cliente.");
            } else {
                Customer customer = GestorCustomer.obtenerOCrearCustomer(customerName);
                customer.returnGame(title);
                println("Videojuego devuelto: " + game.getTitle());
            }
        } catch (Exception e) {
            println("Error al devolver el videojuego: " + e.getMessage());
        }
    }

    private void verVideojuegos() {
        try {
            Menu.printGames(store.getGames());
        } catch (Exception e) {
            println("Error al consultar los videojuegos: " + e.getMessage());
        }
    }

    // ---------------------- MENÚ EMPLEADO ----------------------

    private void menuEmpleado() {
        while (true) {
            Menu.printMenuEmpleado();
            int employeeOption = leerOpcion();

            switch (employeeOption) {
                case 1 -> agregarVideojuego();
                case 2 -> actualizarVideojuego();
                case 3 -> eliminarVideojuego();
                case 4 -> verVideojuegos();
                case 5 -> verRentasActuales();
                case 6 -> {
                    return;
                }
                default -> Menu.printOpcionInvalida();
            }
        }
    }

    private void agregarVideojuego() {
        try {
            println("Título:");
            String title = scanner.nextLine();

            println("Plataforma:");
            String platform = scanner.nextLine();

            println("Precio:");
            double price = Double.parseDouble(scanner.nextLine());

            employee.addGame(new Game(title, platform, price));
            println("Videojuego creado.");
        } catch (Exception e) {
            println("Error al crear el videojuego: " + e.getMessage());
        }
    }

    private void actualizarVideojuego() {
        try {
            println("Ingrese título del videojuego a actualizar:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                println("No encontrado.");
                return;
            }

            println("Nuevo título:");
            String newTitle = scanner.nextLine();

            println("Nueva plataforma:");
            String newPlatform = scanner.nextLine();

            println("Nuevo precio:");
            double newPrice = Double.parseDouble(scanner.nextLine());

            employee.updateGame(game, newTitle, newPlatform, newPrice);
            println("Videojuego actualizado.");
        } catch (Exception e) {
            println("Error al actualizar el videojuego: " + e.getMessage());
        }
    }

    private void eliminarVideojuego() {
        try {
            println("Ingrese título del videojuego a eliminar:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                println("Videojuego no encontrado.");
                return;
            }

            println("¿Seguro que desea eliminar \"" + game.getTitle() + "\"? (si/no)");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("si")) {
                employee.deleteGame(title);
                println("Videojuego eliminado.");
            } else {
                println("Operación cancelada.");
            }
        } catch (Exception e) {
            println("Error al eliminar el videojuego: " + e.getMessage());
        }
    }

    private void verRentasActuales() {
        try {
            Menu.printRentals(store.getActiveRentals());
        } catch (Exception e) {
            println("Error al consultar las rentas: " + e.getMessage());
        }
    }

    // ---------------------- UTILIDADES ----------------------

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}