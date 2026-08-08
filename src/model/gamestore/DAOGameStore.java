package model.gamestore;

import data.Connector;
import data.DBAccess;
import utils.Utils;

import java.sql.ResultSet;

public class DAOGameStore {

    public static int insertarStore(String name) throws Exception {
        String sql = "INSERT INTO t_store (name) VALUES ('" + Utils.escaparCaracteres(name) + "')";
        Connector.getConnection().ejecutarStatement(sql);

        // Recupera id que MySQL acaba de generar (AUTO_INCREMENT)
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    // Como el sistema es de una sola tienda, basta con traer la primera
    // (y única) fila de t_store. Si no hay ninguna todavía, retorna null.
    public static GameStore obtenerStore() throws Exception {
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT id, name FROM t_store LIMIT 1");
        if (rs.next()) {
            return new GameStore(rs.getInt("id"), rs.getString("name"));
        }
        return null;
    }

    public static void actualizarStore(int id, String name) throws Exception {
        String sql = "UPDATE t_store SET name = '" + Utils.escaparCaracteres(name) + "' WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }
}