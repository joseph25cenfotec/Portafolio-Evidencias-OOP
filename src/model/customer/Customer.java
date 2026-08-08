package model.customer;

import model.User;
import model.game.Game;
import model.rental.GestorRental;
import model.rental.Rental;

import java.util.List;

public class Customer extends User {

    private String username;
    private String password;

    // Constructor para registrar un cliente nuevo (id se asigna luego, tras insertar en BD)
    public Customer(String name, String username, String password) {
        super(name);
        this.username = username;
        this.password = password;
    }

    // Constructor de hidratación: reconstruye un cliente ya existente en la BD
    public Customer(int id, String name, String username, String password) {
        super(id, name);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Renta el juego para ESTE cliente únicamente. Retorna false si ya
    // tiene una renta activa de ese mismo título (evita duplicarla).
    public boolean rentGame(Game game) throws Exception {
        if (GestorRental.tieneRentaActiva(getId(), game.getTitle())) {
            return false;
        }
        GestorRental.agregarRental(game, this);
        return true;
    }

    // Marca como devuelta la renta activa de ESTE cliente para ese título.
    // Retorna false si no tenía ninguna renta activa de ese título.
    public boolean returnGame(String gameTitle) throws Exception {
        List<Rental> misRentas = GestorRental.listarRentalsPorCustomer(getId());
        for (Rental r : misRentas) {
            if (r.getReturnDate() == null && r.getGame().getTitle().equalsIgnoreCase(gameTitle)) {
                GestorRental.registrarDevolucion(r.getId());
                return true;
            }
        }
        return false;
    }

    // Todo el historial de rentas de ESTE cliente (para su propio menú self-service)
    public List<Rental> getMisRentas() throws Exception {
        return GestorRental.listarRentalsPorCustomer(getId());
    }
}