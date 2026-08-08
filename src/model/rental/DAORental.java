package model.rental;

import data.Connector;
import model.customer.Customer;
import model.customer.DAOCustomer;
import model.game.DAOGame;
import model.game.Game;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DAORental {

    private static final DateTimeFormatter SQL_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static int insertarRental(Game game, Customer customer, LocalDateTime rentDate) throws Exception {
        String sql = "INSERT INTO t_rentals (id_game, id_customer, rent_date) VALUES ("
                + game.getId() + ", " + customer.getId() + ", '" + rentDate.format(SQL_FORMAT) + "')";
        Connector.getConnection().ejecutarStatement(sql);

        // Recuperamos el id que MySQL acaba de generar (AUTO_INCREMENT)
        ResultSet rs = Connector.getConnection().ejecutarQuery("SELECT LAST_INSERT_ID() AS id");
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static ArrayList<Rental> listarRentals() throws Exception {
        ArrayList<Rental> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, id_game, id_customer, rent_date, return_date FROM t_rentals");
        while (rs.next()) {
            lista.add(mapearRental(rs));
        }
        return lista;
    }

    public static Rental buscarRentalPorId(int id) throws Exception {
        ResultSet rs = Connector.getConnection()
                .ejecutarQuery("SELECT id, id_game, id_customer, rent_date, return_date FROM t_rentals WHERE id = ?", id);
        if (rs.next()) {
            return mapearRental(rs);
        }
        return null;
    }

    // Rentas de UN solo cliente (filtrado en la propia consulta SQL, no en
    // memoria) — es lo que separa "ve todo" (Employee) de "ve lo suyo" (Customer).
    public static ArrayList<Rental> listarRentalsPorCustomer(int idCustomer) throws Exception {
        ArrayList<Rental> lista = new ArrayList<>();
        ResultSet rs = Connector.getConnection().ejecutarQuery(
                "SELECT id, id_game, id_customer, rent_date, return_date FROM t_rentals WHERE id_customer = ?",
                idCustomer);
        while (rs.next()) {
            lista.add(mapearRental(rs));
        }
        return lista;
    }

    public static void registrarDevolucion(int id, LocalDateTime returnDate) throws Exception {
        String sql = "UPDATE t_rentals SET return_date = '" + returnDate.format(SQL_FORMAT) + "' WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    public static void eliminarRental(int id) throws Exception {
        String sql = "DELETE FROM t_rentals WHERE id = " + id;
        Connector.getConnection().ejecutarStatement(sql);
    }

    // Arma un Rental completo a partir de una fila del ResultSet, resolviendo
    // las FK (id_game, id_customer) mediante los DAO correspondientes.
    private static Rental mapearRental(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        int idGame = rs.getInt("id_game");
        int idCustomer = rs.getInt("id_customer");

        Game game = DAOGame.buscarGamePorId(idGame);
        Customer customer = DAOCustomer.buscarCustomerPorId(idCustomer);

        Timestamp rentTimestamp = rs.getTimestamp("rent_date");
        LocalDateTime rentDate = rentTimestamp != null ? rentTimestamp.toLocalDateTime() : null;

        Timestamp returnTimestamp = rs.getTimestamp("return_date");
        LocalDateTime returnDate = returnTimestamp != null ? returnTimestamp.toLocalDateTime() : null;

        return new Rental(id, game, customer, rentDate, returnDate);
    }
}