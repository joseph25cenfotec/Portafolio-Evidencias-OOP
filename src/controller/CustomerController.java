package controller;

import model.customer.Customer;
import model.game.Game;
import model.gamestore.GameStore;
import model.rental.Rental;
import service.RentalService;
import view.Menu;

import java.util.List;
import java.util.Scanner;

import static java.lang.IO.*;
import static utils.Utils.leerOpcion;

public class CustomerController {

    private final Scanner scanner;
    private final GameStore store;
    private final Customer customer;

    public CustomerController(Scanner scanner, GameStore store, Customer customer) {
        this.scanner = scanner;
        this.store = store;
        this.customer = customer;
    }

    public void run() {
        while (true) {
            Menu.printMenuClienteSelfService();
            int option = leerOpcion(scanner);

            switch (option) {
                case 1 -> verCatalogo();
                case 2 -> rentarVideojuego();
                case 3 -> devolverVideojuego();
                case 4 -> verMisRentas();
                case 5 -> {
                    println("Sesión cerrada.");
                    return;
                }
                default -> Menu.printOpcionInvalida();
            }
        }
    }

    private void verCatalogo() {
        try {
            // El catálogo sí es global/compartido: todos los clientes ven los mismos juegos
            Menu.printGames(store.getGames());
        } catch (Exception e) {
            println("Error al consultar los videojuegos: " + e.getMessage());
        }
    }

    private void rentarVideojuego() {
        try {
            println("Ingrese título del videojuego:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);
            if (game == null) {
                println("Videojuego no encontrado.");
                return;
            }

            RentalService.rentar(customer, game);
        } catch (Exception e) {
            println("Error al rentar el videojuego: " + e.getMessage());
        }
    }

    private void devolverVideojuego() {
        try {
            println("Ingrese título del videojuego a devolver:");
            String title = scanner.nextLine();

            RentalService.devolver(customer, title);
        } catch (Exception e) {
            println("Error al devolver el videojuego: " + e.getMessage());
        }
    }

    private void verMisRentas() {
        try {
            // Escopeado por id del propio cliente (WHERE id_customer = ? en la BD)
            List<Rental> misRentas = customer.getMisRentas();
            Menu.printRentals(misRentas);
        } catch (Exception e) {
            println("Error al consultar tus rentas: " + e.getMessage());
        }
    }
}