package model.rental;

import model.customer.Customer;
import model.game.Game;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorRental {

    public static int agregarRental(Game game, Customer customer) throws Exception {
        return DAORental.insertarRental(game, customer, LocalDateTime.now());
    }

    public static ArrayList<Rental> listarRentals() throws Exception {
        return DAORental.listarRentals();
    }

    public static Rental buscarRentalPorId(int id) throws Exception {
        return DAORental.buscarRentalPorId(id);
    }

    // Solo las rentas de un cliente específico (self-service de Customer).
    public static ArrayList<Rental> listarRentalsPorCustomer(int idCustomer) throws Exception {
        return DAORental.listarRentalsPorCustomer(idCustomer);
    }

    // ¿Ese cliente tiene ahora mismo una renta activa (no devuelta) de ese título?
    public static boolean tieneRentaActiva(int idCustomer, String gameTitle) throws Exception {
        return listarRentalsPorCustomer(idCustomer).stream()
                .anyMatch(r -> r.getReturnDate() == null && r.getGame().getTitle().equalsIgnoreCase(gameTitle));
    }

    // Marca la renta como devuelta (UPDATE de return_date), en vez de
    // eliminarla, para conservar el historial de rentas.
    public static void registrarDevolucion(int id) throws Exception {
        DAORental.registrarDevolucion(id, LocalDateTime.now());
    }

    public static void eliminarRental(int id) throws Exception {
        DAORental.eliminarRental(id);
    }
}