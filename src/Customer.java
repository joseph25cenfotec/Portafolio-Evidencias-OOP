import static utils.IdGenerator.generateUUID;

public class Customer {

    private String id;
    private String name;

    public Customer(String name) {
        this.id = generateUUID();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rentGame(GameStore store, Game game) {
        store.getRentals().add(new Rental(game, this));
    }

    public void returnGame(GameStore store, String gameTitle) {
        store.getRentals().removeIf(
                rental ->
                        rental.getGame().getTitle().equalsIgnoreCase(gameTitle) && rental.getCustomer().getName().equalsIgnoreCase(getName())
        );
    }
}