package model;

import model.Game.Game;
import model.Game.GestorGame;
import model.Rental.GestorRental;
import model.Rental.Rental;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GameStore ya no guarda listas en memoria: es un orquestador que
 * delega toda lectura/escritura a los Gestores (Game, Rental). No
 * tiene su propia tabla en la BD.
 */
public class GameStore {

    private final String name;

    public GameStore(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Game findGameByTitle(String title) throws Exception {
        return GestorGame.buscarGamePorTitulo(title);
    }

    // Un juego está "rentado" si existe una renta activa (return_date == null)
    // para ese título y ese cliente.
    public boolean isRented(String gameTitle, String customerName) throws Exception {
        return getActiveRentals().stream()
                .anyMatch(r ->
                        r.getGame().getTitle().equalsIgnoreCase(gameTitle)
                                && r.getCustomer().getName().equalsIgnoreCase(customerName));
    }

    public List<Game> getGames() throws Exception {
        return GestorGame.listarGames();
    }

    // Historial completo de rentas (incluye devueltas)
    public List<Rental> getRentals() throws Exception {
        return GestorRental.listarRentals();
    }

    // Solo las rentas que todavía no se han devuelto
    public List<Rental> getActiveRentals() throws Exception {
        return getRentals().stream()
                .filter(r -> r.getReturnDate() == null)
                .collect(Collectors.toList());
    }
}