package model.employee;

import data.Connector;
import data.DBAccess;
import utils.Utils;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOEmployee {

    public static int insertarEmployee(String name, String username, String password, String role) throws Exception {
        String sql = "INSERT INTO t_employees (name, username, password, role) VALUES ('"
                + Utils.escaparCaracteres(name) + "', '" + Utils.escaparCaracteres(username) + "', '"
                + Utils.escaparCaracteres(password) + "', '" + Utils.escaparCaracteres(role) + "')";
        Connector.getConnection().ejecutarStatement(sql);

        // Recuperamos el id que MySQL acaba de generar (AUTO_INCREMENT)
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static ArrayList<Employee> listarEmployees() throws Exception {
        ArrayList<Employee> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name, username, password, role FROM t_employees");
        while (rs.next()) {
            lista.add(mapearEmployee(rs));
        }
        return lista;
    }

    public static Employee buscarEmployeePorId(int id) throws Exception {
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name, username, password, role FROM t_employees WHERE id = ?", id);
        if (rs.next()) {
            return mapearEmployee(rs);
        }
        return null;
    }

    // Clave para el login: busca por username (único).
    public static Employee buscarEmployeePorUsername(String username) throws Exception {
        ResultSet rs = Connector.getConnection().ejecutarQuery(
                "SELECT id, name, username, password, role FROM t_employees WHERE username = '"
                        + Utils.escaparCaracteres(username) + "'");
        if (rs.next()) {
            return mapearEmployee(rs);
        }
        return null;
    }

    public static void actualizarEmployee(int id, String name, String username, String password, String role) throws Exception {
        String sql = "UPDATE t_employees SET name = '" + Utils.escaparCaracteres(name)
                + "', username = '" + Utils.escaparCaracteres(username)
                + "', password = '" + Utils.escaparCaracteres(password)
                + "', role = '" + Utils.escaparCaracteres(role)
                + "' WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    public static void eliminarEmployee(int id) throws Exception {
        String sql = "DELETE FROM t_employees WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    private static Employee mapearEmployee(ResultSet rs) throws Exception {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role")
        );
    }
}