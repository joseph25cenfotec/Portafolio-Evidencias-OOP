package data;

import com.mysql.cj.Query;

import java.sql.*;

public class DBAccess {
    // Atributos
    private final Connection connection;
    private Statement statement = null;
    private Query query = null;
    private PreparedStatement preparedStatement = null;

    // Métodos
    // Constructor
    public DBAccess(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    // Rutina que recibe un String que contiene una sentencia de MySQL y la ejecuta utilizando
    // una rutina de un objeto de la clase Connection.
    public void ejecutarStatement(String pStatement) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(pStatement);
    }

    public ResultSet ejecutarQuery(String pQuery) throws SQLException {
        ResultSet resultado;
        statement = connection.createStatement();
        resultado = statement.executeQuery(pQuery);
        return resultado;
    }

    public ResultSet ejecutarQuery(String pQuery, int pValor) throws SQLException {
        ResultSet resultado;
        preparedStatement = connection.prepareStatement(pQuery);
        preparedStatement.setInt(1, pValor);
        resultado = preparedStatement.executeQuery();
        return resultado;
    }
}
