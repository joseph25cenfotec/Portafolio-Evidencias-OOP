package view;

import model.game.Game;
import model.rental.Rental;
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

    // Selección inicial: ¿la persona entra como Empleado o como Cliente?
    public static void printMenuRol() {
        println("\n=== ¿CÓMO DESEA INGRESAR? ===");
        println("1. Empleado");
        println("2. Cliente");
        println("3. Salir");
        print("Introduzca una opción: ");
    }

    // rol: "EMPLEADOS" o "CLIENTES", para reutilizar el mismo menú de acceso
    public static void printMenuAuth(String rol) {
        println("\n=== ACCESO " + rol.toUpperCase() + " ===");
        println("1. Iniciar sesión");
        println("2. Registrarse");
        println("3. Volver");
        print("Introduzca una opción: ");
    }

    public static void printMenuPrincipal() {
        println("\n=== MENÚ PRINCIPAL ===");
        println("1. Cliente");
        println("2. Empleado");
        println("3. Salir");
        print("Introduzca una opción: ");
    }

    // Menú del lado del empleado: opera sobre un cliente (lo busca por username)
    public static void printMenuCliente() {
        println("\n=== MENÚ CLIENTE ===");
        println("1. Rentar videojuego");
        println("2. Devolver videojuego");
        println("3. Ver videojuegos");
        println("4. Volver");
        print("Introduzca una opción: ");
    }

    // Menú self-service: el propio cliente logueado, solo ve/toca lo suyo
    public static void printMenuClienteSelfService() {
        println("\n=== MI CUENTA ===");
        println("1. Ver catálogo de videojuegos");
        println("2. Rentar videojuego");
        println("3. Devolver videojuego");
        println("4. Ver mis rentas");
        println("5. Cerrar sesión");
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

    public static void printSistemaCerrado() {
        println("Sistema cerrado.");
    }

    public static void printHastaLuego() {
        println("Hasta luego.");
    }

    // ---------------------- TIENDA (bootstrap) ----------------------

    public static void promptNombreTienda() {
        println("Ingrese el nombre de la tienda:");
    }

    public static void printBienvenidoATienda(String storeName) {
        println("Bienvenido a " + storeName);
    }

    // ---------------------- LOGIN / REGISTRO ----------------------

    public static void promptUsername() {
        println("Usuario:");
    }

    public static void promptPassword() {
        println("Contraseña:");
    }

    public static void promptNombreCompleto() {
        println("Nombre completo:");
    }

    public static void promptRol() {
        println("Rol (ej. Administrador, Cajero):");
    }

    public static void printRegistroExitoso() {
        println("Registro exitoso.");
    }

    public static void printCredencialesInvalidas() {
        println("Usuario o contraseña incorrectos.");
    }

    public static void printSesionIniciada(String name) {
        println("\nSesión iniciada como " + name);
    }

    public static void printSesionIniciadaConRol(String name, String role) {
        println("\nSesión iniciada como " + name + " (" + role + ")");
    }
}