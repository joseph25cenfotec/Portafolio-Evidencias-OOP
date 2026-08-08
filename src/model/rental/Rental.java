package model.rental;

import model.customer.Customer;
import model.game.Game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rental {

    private int id;
    private Game game;
    private Customer customer;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    // Metodo para formatear fechas en formato dd-MM-yy (Día-Mes-Año)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Constructor para crear una renta nueva (id se asigna luego, tras insertar en BD)
    public Rental(Game game, Customer customer) {
        this.game = game;
        this.customer = customer;
        this.rentDate = LocalDateTime.now();
    }

    // Constructor de hidratación: reconstruye una renta ya existente en la BD
    public Rental(int id, Game game, Customer customer, LocalDateTime rentDate, LocalDateTime returnDate) {
        this.id = id;
        this.game = game;
        this.customer = customer;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return customer.getName() + " | " + game.getTitle() + " | " + rentDate.format(formatter);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LocalDateTime getRentDate() { return rentDate; }
    public void setRentDate(LocalDateTime rentDate) { this.rentDate = rentDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
}