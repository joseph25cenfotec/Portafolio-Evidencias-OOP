package model.Customer;

import data.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOCustomer {

    public static int insertarCustomer(String name) throws Exception {
        String sql = "INSERT INTO t_customers (name) VALUES ('" + name + "')";
        Connector.getConnection().ejecutarStatement(sql);

        // Recuperamos el id que MySQL acaba de generar (AUTO_INCREMENT)
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static ArrayList<Customer> listarCustomers() throws Exception {
        ArrayList<Customer> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT id, name FROM t_customers");
        while (rs.next()) {
            lista.add(new Customer(rs.getInt("id"), rs.getString("name")));
        }
        return lista;
    }

    public static Customer buscarCustomerPorId(int id) throws Exception {
        // Usamos el overload con PreparedStatement (?, id) para evitar concatenar el id
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name FROM t_customers WHERE id = ?", id);
        if (rs.next()) {
            return new Customer(rs.getInt("id"), rs.getString("name"));
        }
        return null;
    }

    public static Customer buscarCustomerPorNombre(String name) throws Exception {
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name FROM t_customers WHERE name = '" + name + "'");
        if (rs.next()) {
            return new Customer(rs.getInt("id"), rs.getString("name"));
        }
        return null;
    }

    public static void actualizarCustomer(int id, String name) throws Exception {
        String sql = "UPDATE t_customers SET name = '" + name + "' WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    public static void eliminarCustomer(int id) throws Exception {
        String sql = "DELETE FROM t_customers WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }
}