package service;

import model.customer.Customer;
import model.game.Game;

import static java.lang.IO.*;

/**
 * Lógica de rentar/devolver compartida entre EmployeeController
 * (empleado asistiendo a un cliente) y CustomerController (self-service).
 * Evita duplicar el mismo bloque de "intentar y avisar el resultado".
 */
public class RentalService {

    public static void rentar(Customer customer, Game game) throws Exception {
        boolean rentado = customer.rentGame(game);
        if (rentado) {
            println("Videojuego rentado: " + game.getTitle());
            println("Precio: " + game.getPrice());
        } else {
            println("Ese cliente ya tiene ese videojuego rentado.");
        }
    }

    public static void devolver(Customer customer, String title) throws Exception {
        boolean devuelto = customer.returnGame(title);
        if (devuelto) {
            println("Videojuego devuelto: " + title);
        } else {
            println("Ese cliente no tiene ese videojuego rentado actualmente.");
        }
    }
}