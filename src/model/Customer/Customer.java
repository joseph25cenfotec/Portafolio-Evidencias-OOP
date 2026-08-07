package model.Customer;

import model.Game.Game;
import model.Rental.GestorRental;
import model.Rental.Rental;
import model.User;

import java.util.List;

public class Customer extends User {

    // Constructor para crear un cliente nuevo (id se asigna luego, tras insertar en BD)
    public Customer(String name) {
        super(name);
    }

    // Constructor de hidratación: reconstruye un cliente ya existente en la BD
    public Customer(int id, String name) {
        super(id, name);
    }

    // Requiere que este Customer ya tenga un id real (ya persistido en t_customers).
    public void rentGame(Game game) throws Exception {
        GestorRental.agregarRental(game, this);
    }

    // Marca como devuelta la renta activa de este cliente para ese título,
    // en vez de eliminarla, para conservar el historial.
    public void returnGame(String gameTitle) throws Exception {
        List<Rental> rentals = GestorRental.listarRentals();
        for (Rental r : rentals) {
            if (r.getReturnDate() == null
                    && r.getGame().getTitle().equalsIgnoreCase(gameTitle)
                    && r.getCustomer().getName().equalsIgnoreCase(getName())) {
                GestorRental.registrarDevolucion(r.getId());
                return;
            }
        }
    }
}