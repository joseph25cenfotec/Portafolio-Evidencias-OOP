package model.customer;

import data.Connector;
import data.DBAccess;
import utils.Utils;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOCustomer {

    public static int insertarCustomer(String name, String username, String password) throws Exception {
        String sql = "INSERT INTO t_customers (name, username, password) VALUES ('"
                + Utils.escaparCaracteres(name) + "', '" + Utils.escaparCaracteres(username) + "', '"
                + Utils.escaparCaracteres(password) + "')";
        Connector.getConnection().ejecutarStatement(sql);

        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static ArrayList<Customer> listarCustomers() throws Exception {
        ArrayList<Customer> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name, username, password FROM t_customers");
        while (rs.next()) {
            lista.add(mapearCustomer(rs));
        }
        return lista;
    }

    public static Customer buscarCustomerPorId(int id) throws Exception {
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name, username, password FROM t_customers WHERE id = ?", id);
        if (rs.next()) {
            return mapearCustomer(rs);
        }
        return null;
    }

    // Clave para el login: busca por username (único).
    public static Customer buscarCustomerPorUsername(String username) throws Exception {
        ResultSet rs = Connector.getConnection().ejecutarQuery(
                "SELECT id, name, username, password FROM t_customers WHERE username = '"
                        + Utils.escaparCaracteres(username) + "'");
        if (rs.next()) {
            return mapearCustomer(rs);
        }
        return null;
    }

    public static void actualizarCustomer(int id, String name, String username, String password) throws Exception {
        String sql = "UPDATE t_customers SET name = '" + Utils.escaparCaracteres(name)
                + "', username = '" + Utils.escaparCaracteres(username)
                + "', password = '" + Utils.escaparCaracteres(password)
                + "' WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    public static void eliminarCustomer(int id) throws Exception {
        String sql = "DELETE FROM t_customers WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    private static Customer mapearCustomer(ResultSet rs) throws Exception {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}