import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStore {

    private String id;
    private String name;
    private List<Game> games;
    private List<Rental> rentals; // NUEVO

    public GameStore(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.games = new ArrayList<>();
        this.rentals = new ArrayList<>(); // NUEVO
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public Game findGameByTitle(String title) {
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(title)) {
                return g;
            }
        }
        return null;
    }

    public void deleteGame(String title) {
        games.removeIf(g -> g.getTitle().equalsIgnoreCase(title));
    }

    public List<Game> getGames() {
        return games;
    }

    // NUEVOS
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void removeRental(String gameTitle) {
        rentals.removeIf(r -> r.getGame().getTitle().equalsIgnoreCase(gameTitle));
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public boolean isRented(String gameTitle) {
        return rentals.stream()
                .anyMatch(r -> r.getGame().getTitle().equalsIgnoreCase(gameTitle));
    }
}