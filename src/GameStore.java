import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStore {

    private String id;
    private String name;

    private List<Game> games;
    private List<Rental> rentals;

    public GameStore(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.games = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public Game findGameByTitle(String title) {
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(title)) {
                return g;
            }
        }
        return null;
    }

    public boolean isRented(String customerName, String gameTitle) {
        return rentals.stream()
                .anyMatch(
                        r ->
                                r.getGame()
                                        .getTitle()
                                        .equalsIgnoreCase(gameTitle)
                                        &&
                                        r.getCustomer()
                                                .getName()
                                                .equalsIgnoreCase(customerName)
                );
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}