import model.GameStore;
import model.Employee.Employee;
import view.Menu;
import controller.Controller;
import java.util.Scanner;
import static java.lang.IO.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu.printBienvenida();

        println("Ingrese el nombre de la tienda:");
        String storeName = scanner.nextLine();

        // Inicialización del nombre de la tienda y el usuario empleado
        GameStore store = new GameStore(storeName);
        Employee employee = new Employee("Johan", "Administrador");

        // Ciclo de menús en el controlador
        Controller controller = new Controller(scanner, store, employee);
        controller.run();
    }
}