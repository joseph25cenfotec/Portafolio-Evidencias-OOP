package model.customer;

import java.util.ArrayList;

public class GestorCustomer {

    // Registra un cliente nuevo. Lanza excepción si el username ya existe.
    public static Customer registrarCustomer(String name, String username, String password) throws Exception {
        Customer existente = DAOCustomer.buscarCustomerPorUsername(username);
        if (existente != null) {
            throw new Exception("El usuario '" + username + "' ya existe.");
        }
        int id = DAOCustomer.insertarCustomer(name, username, password);
        return new Customer(id, name, username, password);
    }

    // Valida credenciales. Retorna el Customer si son correctas, o null si
    // el usuario no existe o la contraseña no coincide.
    public static Customer login(String username, String password) throws Exception {
        Customer customer = DAOCustomer.buscarCustomerPorUsername(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    public static Customer buscarCustomerPorUsername(String username) throws Exception {
        return DAOCustomer.buscarCustomerPorUsername(username);
    }

    public static ArrayList<Customer> listarCustomers() throws Exception {
        return DAOCustomer.listarCustomers();
    }

    public static Customer buscarCustomerPorId(int id) throws Exception {
        return DAOCustomer.buscarCustomerPorId(id);
    }

    public static void actualizarCustomer(int id, String name, String username, String password) throws Exception {
        DAOCustomer.actualizarCustomer(id, name, username, password);
    }

    public static void eliminarCustomer(int id) throws Exception {
        DAOCustomer.eliminarCustomer(id);
    }
}