import controller.AuthController;
import controller.CustomerController;
import controller.EmployeeController;
import model.customer.Customer;
import model.employee.Employee;
import model.gamestore.GameStore;
import model.gamestore.GestorGameStore;
import view.Menu;

import java.util.Scanner;

import static java.lang.IO.*;
import static utils.Utils.leerOpcion;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu.printBienvenida();

        try {
            GameStore store = GestorGameStore.obtenerStore();

            if (store == null) {
                // Primera vez que corre el sistema: se pide el nombre una única vez.
                Menu.promptNombreTienda();
                String storeName = scanner.nextLine();
                store = GestorGameStore.crearStore(storeName);
            }

            Menu.printBienvenidoATienda(store.getName());

            AuthController auth = new AuthController(scanner);

            // Ciclo principal: elegir rol -> autenticarse -> operar -> volver a elegir rol
            while (true) {
                Menu.printMenuRol();
                int rolOption = leerOpcion(scanner);

                switch (rolOption) {
                    case 1 -> {
                        Employee employee = auth.autenticarEmployee();
                        if (employee != null) {
                            Menu.printSesionIniciadaConRol(employee.getName(), employee.getRole());
                            new EmployeeController(scanner, store, employee).run();
                        }
                    }
                    case 2 -> {
                        Customer customer = auth.autenticarCustomer();
                        if (customer != null) {
                            Menu.printSesionIniciada(customer.getName());
                            new CustomerController(scanner, store, customer).run();
                        }
                    }
                    case 3 -> {
                        Menu.printHastaLuego();
                        return;
                    }
                    default -> Menu.printOpcionInvalida();
                }
            }
        } catch (Exception e) {
            println("Error al iniciar el sistema: " + e.getMessage());
        }
    }
}