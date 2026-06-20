public class Customer extends User {

    public Customer(String name) {
        super(name);
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