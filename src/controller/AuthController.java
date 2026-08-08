package controller;

import model.customer.Customer;
import model.customer.GestorCustomer;
import model.employee.Employee;
import model.employee.GestorEmployee;
import view.Menu;

import java.util.Scanner;
import static utils.Utils.leerOpcion;

/**
 * Controlador de acceso: maneja login/registro tanto de Employee como
 * de Customer. Main solo lo invoca y decide a qué controller pasar
 * la sesión resultante.
 */
public class AuthController {

    private final Scanner scanner;

    public AuthController(Scanner scanner) {
        this.scanner = scanner;
    }

    // ---------------------- EMPLEADO ----------------------

    public Employee autenticarEmployee() {
        while (true) {
            Menu.printMenuAuth("EMPLEADOS");
            int option = leerOpcion(scanner);

            switch (option) {
                case 1 -> {
                    Employee employee = loginEmployee();
                    if (employee != null) {
                        return employee;
                    }
                }
                case 2 -> {
                    Employee employee = registroEmployee();
                    if (employee != null) {
                        return employee;
                    }
                }
                case 3 -> {
                    return null; // vuelve al menú de rol
                }
                default -> Menu.printOpcionInvalida();
            }
        }
    }

    private Employee loginEmployee() {
        try {
            Menu.promptUsername();
            String username = scanner.nextLine();

            Menu.promptPassword();
            String password = scanner.nextLine();

            Employee employee = GestorEmployee.login(username, password);
            if (employee == null) {
                Menu.printCredencialesInvalidas();
            }
            return employee;
        } catch (Exception e) {
            Menu.printMessage("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }

    private Employee registroEmployee() {
        try {
            Menu.promptNombreCompleto();
            String name = scanner.nextLine();

            Menu.promptUsername();
            String username = scanner.nextLine();

            Menu.promptPassword();
            String password = scanner.nextLine();

            Menu.promptRol();
            String role = scanner.nextLine();

            Employee employee = GestorEmployee.registrarEmployee(name, username, password, role);
            Menu.printRegistroExitoso();
            return employee;
        } catch (Exception e) {
            Menu.printMessage("Error al registrarse: " + e.getMessage());
            return null;
        }
    }

    // ---------------------- CLIENTE ----------------------

    public Customer autenticarCustomer() {
        while (true) {
            Menu.printMenuAuth("CLIENTES");
            int option = leerOpcion(scanner);

            switch (option) {
                case 1 -> {
                    Customer customer = loginCustomer();
                    if (customer != null) {
                        return customer;
                    }
                }
                case 2 -> {
                    Customer customer = registroCustomer();
                    if (customer != null) {
                        return customer;
                    }
                }
                case 3 -> {
                    return null; // vuelve al menú de rol
                }
                default -> Menu.printOpcionInvalida();
            }
        }
    }

    private Customer loginCustomer() {
        try {
            Menu.promptUsername();
            String username = scanner.nextLine();

            Menu.promptPassword();
            String password = scanner.nextLine();

            Customer customer = GestorCustomer.login(username, password);
            if (customer == null) {
                Menu.printCredencialesInvalidas();
            }
            return customer;
        } catch (Exception e) {
            Menu.printMessage("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }

    private Customer registroCustomer() {
        try {
            Menu.promptNombreCompleto();
            String name = scanner.nextLine();

            Menu.promptUsername();
            String username = scanner.nextLine();

            Menu.promptPassword();
            String password = scanner.nextLine();

            Customer customer = GestorCustomer.registrarCustomer(name, username, password);
            Menu.printRegistroExitoso();
            return customer;
        } catch (Exception e) {
            Menu.printMessage("Error al registrarse: " + e.getMessage());
            return null;
        }
    }
}