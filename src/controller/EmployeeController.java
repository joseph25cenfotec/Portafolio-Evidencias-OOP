package controller;

import model.employee.Employee;
import model.game.Game;
import model.gamestore.GameStore;
import view.Menu;

import java.util.Scanner;
import static java.lang.IO.*;
import static utils.Utils.leerOpcion;

/**
 * Controller del empleado autenticado. Ve TODO el sistema: catálogo
 * completo, todas las rentas activas, y puede operar en nombre de
 * cualquier cliente (lo busca por username).
 */
public class EmployeeController {

    private final Scanner scanner;
    private final GameStore store;
    private final Employee employee;

    public EmployeeController(Scanner scanner, GameStore store, Employee employee) {
        this.scanner = scanner;
        this.store = store;
        this.employee = employee;
    }

    public void run() {
        menuEmpleado();
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
            int employeeOption = leerOpcion(scanner);

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
}