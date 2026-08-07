package model.Game;

import data.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOGame {

    public static int insertarGame(String title, String platform, double price) throws Exception {
        String sql = "INSERT INTO t_games (title, platform, price) VALUES ('"
                + title + "', '" + platform + "', " + price + ")";
        Connector.getConnection().ejecutarStatement(sql);

        // Recuperamos el id que MySQL acaba de generar (AUTO_INCREMENT)
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static ArrayList<Game> listarGames() throws Exception {
        ArrayList<Game> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT id, title, platform, price FROM t_games");
        while (rs.next()) {
            lista.add(new Game(rs.getInt("id"), rs.getString("title"), rs.getString("platform"), rs.getDouble("price")));
        }
        return lista;
    }

    public static Game buscarGamePorId(int id) throws Exception {
        // Usamos el overload con PreparedStatement (?, id) para evitar concatenar el id
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, title, platform, price FROM t_games WHERE id = ?", id);
        if (rs.next()) {
            return new Game(rs.getInt("id"), rs.getString("title"), rs.getString("platform"), rs.getDouble("price"));
        }
        return null;
    }

    // Nota: DBAccess actualmente solo soporta PreparedStatement con un
    // único parámetro int, así que para filtrar por título (String)
    // concatenamos directamente (mismo TODO de siempre: migrar a
    // PreparedStatement parametrizado cuando se limpie DBAccess).
    public static Game buscarGamePorTitulo(String title) throws Exception {
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, title, platform, price FROM t_games WHERE title = '" + title + "'");
        if (rs.next()) {
            return new Game(rs.getInt("id"), rs.getString("title"), rs.getString("platform"), rs.getDouble("price"));
        }
        return null;
    }

    public static void actualizarGame(int id, String title, String platform, double price) throws Exception {
        String sql = "UPDATE t_games SET title = '" + title + "', platform = '" + platform
                + "', price = " + price + " WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    public static void eliminarGame(int id) throws Exception {
        String sql = "DELETE FROM t_games WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }
}