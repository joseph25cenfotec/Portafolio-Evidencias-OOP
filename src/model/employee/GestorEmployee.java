package model.employee;

import java.util.ArrayList;

public class GestorEmployee {

    // Registra un empleado nuevo. Lanza excepción si el username ya existe,
    // para no permitir duplicados.
    public static Employee registrarEmployee(String name, String username, String password, String role) throws Exception {
        Employee existente = DAOEmployee.buscarEmployeePorUsername(username);
        if (existente != null) {
            throw new Exception("El usuario '" + username + "' ya existe.");
        }
        int id = DAOEmployee.insertarEmployee(name, username, password, role);
        return new Employee(id, name, username, password, role);
    }

    // Valida credenciales. Retorna el Employee si son correctas, o null si
    // el usuario no existe o la contraseña no coincide.
    public static Employee login(String username, String password) throws Exception {
        Employee employee = DAOEmployee.buscarEmployeePorUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    public static ArrayList<Employee> listarEmployees() throws Exception {
        return DAOEmployee.listarEmployees();
    }

    public static Employee buscarEmployeePorId(int id) throws Exception {
        return DAOEmployee.buscarEmployeePorId(id);
    }

    public static void actualizarEmployee(int id, String name, String username, String password, String role) throws Exception {
        DAOEmployee.actualizarEmployee(id, name, username, password, role);
    }

    public static void eliminarEmployee(int id) throws Exception {
        DAOEmployee.eliminarEmployee(id);
    }
}