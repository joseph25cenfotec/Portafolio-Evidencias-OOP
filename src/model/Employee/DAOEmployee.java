package model.Employee;

import data.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOEmployee {

    public static int insertarEmployee(String name, String role) throws Exception {
        String sql = "INSERT INTO t_employees (name, role) VALUES ('" + name + "', '" + role + "')";
        Connector.getConnection().ejecutarStatement(sql);

        // Recupera id que MySQL acaba de generar (AUTO_INCREMENT)
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static ArrayList<Employee> listarEmployees() throws Exception {
        ArrayList<Employee> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT id, name, role FROM t_employees");
        while (rs.next()) {
            lista.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role")));
        }
        return lista;
    }

    public static Employee buscarEmployeePorId(int id) throws Exception {
        // Usamos el overload con PreparedStatement (?, id) para evitar concatenar el id
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, name, role FROM t_employees WHERE id = ?", id);
        if (rs.next()) {
            return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role"));
        }
        return null;
    }

    public static void actualizarEmployee(int id, String name, String role) throws Exception {
        String sql = "UPDATE t_employees SET name = '" + name + "', role = '" + role
                + "' WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    public static void eliminarEmployee(int id) throws Exception {
        String sql = "DELETE FROM t_employees WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }
}