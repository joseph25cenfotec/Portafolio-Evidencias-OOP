package model.Customer;

import java.util.ArrayList;

public class GestorCustomer {

    public static int agregarCustomer(String name) throws Exception {
        return DAOCustomer.insertarCustomer(name);
    }

    public static ArrayList<Customer> listarCustomers() throws Exception {
        return DAOCustomer.listarCustomers();
    }

    public static Customer buscarCustomerPorId(int id) throws Exception {
        return DAOCustomer.buscarCustomerPorId(id);
    }

    public static Customer buscarCustomerPorNombre(String name) throws Exception {
        return DAOCustomer.buscarCustomerPorNombre(name);
    }

    // Busca el cliente por nombre; si no existe, lo crea. Evita duplicar
    // clientes y garantiza que siempre tenga un id real antes de rentar.
    public static Customer obtenerOCrearCustomer(String name) throws Exception {
        Customer existente = buscarCustomerPorNombre(name);
        if (existente != null) {
            return existente;
        }
        int id = agregarCustomer(name);
        return new Customer(id, name);
    }

    public static void actualizarCustomer(int id, String name) throws Exception {
        DAOCustomer.actualizarCustomer(id, name);
    }

    public static void eliminarCustomer(int id) throws Exception {
        DAOCustomer.eliminarCustomer(id);
    }
}