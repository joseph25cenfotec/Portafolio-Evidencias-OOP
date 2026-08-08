package model.gamestore;

import model.game.Game;
import model.game.GestorGame;
import model.rental.GestorRental;
import model.rental.Rental;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GameStore ahora sí se persiste (una sola fila en t_store: el sistema
 * es de una sola tienda). Sus métodos de negocio siguen delegando a
 * GestorGame/GestorRental para el catálogo y las rentas.
 */
public class GameStore {

    private int id;
    private final String name;

    // Constructor para crear una tienda nueva (id se asigna luego, tras insertar en BD)
    public GameStore(String name) {
        this.name = name;
    }

    // Constructor de hidratación: reconstruye la tienda ya existente en la BD
    public GameStore(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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