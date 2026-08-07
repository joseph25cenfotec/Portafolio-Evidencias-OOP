package model.Employee;

import java.util.ArrayList;

public class GestorEmployee {

    public static int agregarEmployee(String name, String role) throws Exception {
        return DAOEmployee.insertarEmployee(name, role);
    }

    public static ArrayList<Employee> listarEmployees() throws Exception {
        return DAOEmployee.listarEmployees();
    }

    public static Employee buscarEmployeePorId(int id) throws Exception {
        return DAOEmployee.buscarEmployeePorId(id);
    }

    public static void actualizarEmployee(int id, String name, String role) throws Exception {
        DAOEmployee.actualizarEmployee(id, name, role);
    }

    public static void eliminarEmployee(int id) throws Exception {
        DAOEmployee.eliminarEmployee(id);
    }
}