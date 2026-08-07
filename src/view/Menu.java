package view;

import model.Game.Game;
import model.Rental.Rental;
import java.util.List;
import static java.lang.IO.*;

/**
 * Vista del sistema: se encarga exclusivamente de mostrar información
 * por consola. No contiene lógica de negocio ni maneja el Scanner.
 */
public class Menu {

    public static void printBienvenida() {
        println("|| Sistema de Renta de Videojuegos ||");
        println("--------------------------------------");
    }

    public static void printMenuPrincipal() {
        println("\n=== MENÚ PRINCIPAL ===");
        println("1. Cliente");
        println("2. Empleado");
        println("3. Salir");
        print("Introduzca una opción: ");
    }

    public static void printMenuCliente() {
        println("\n=== MENÚ CLIENTE ===");
        println("1. Rentar videojuego");
        println("2. Devolver videojuego");
        println("3. Ver videojuegos");
        println("4. Volver");
        print("Introduzca una opción: ");
    }

    public static void printMenuEmpleado() {
        println("\n=== MENÚ EMPLEADO ===");
        println("1. Agregar nuevo videojuego");
        println("2. Actualizar información de un videojuego");
        println("3. Eliminar un videojuego");
        println("4. Ver videojuegos");
        println("5. Ver rentas actuales");
        println("6. Volver");
        print("Introduzca una opción: ");
    }

    public static void printGames(List<Game> games) {
        println("\n=== VIDEOJUEGOS ===");
        if (games.isEmpty()) {
            println("No se encontraron videojuegos");
        } else {
            for (Game g : games) {
                println("- " + g.getTitle() + " | " + g.getPlatform() + " | $" + g.getPrice());
            }
        }
    }

    public static void printRentals(List<Rental> rentals) {
        println("\n=== RENTAS ACTUALES ===");
        if (rentals.isEmpty()) {
            println("No hay videojuegos rentados actualmente.");
        } else {
            for (Rental rental : rentals) {
                println("- " + rental);
            }
        }
    }

    public static void printMessage(String message) {
        println(message);
    }

    public static void printOpcionInvalida() {
        println("Opción inválida.");
    }
}