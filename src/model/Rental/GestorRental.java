package model.Rental;

import model.Customer.Customer;
import model.Game.Game;

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

    // Marca la renta como devuelta (UPDATE de return_date), en vez de
    // eliminarla, para conservar el historial de rentas.
    public static void registrarDevolucion(int id) throws Exception {
        DAORental.registrarDevolucion(id, LocalDateTime.now());
    }

    public static void eliminarRental(int id) throws Exception {
        DAORental.eliminarRental(id);
    }
}