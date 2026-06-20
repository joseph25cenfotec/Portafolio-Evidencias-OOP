public class Employee extends User{

    private String role;

    public Employee(String name, String role) {
        super(name);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void addGame(GameStore store, Game game) {
        store.getGames().add(game);
    }

    public void deleteGame(GameStore store, String title) {
        store.getGames()
                .removeIf(g -> g.getTitle().equalsIgnoreCase(title));
    }

    public void updateGame(Game game, String title, String platform, double price) {
        game.setTitle(title);
        game.setPlatform(platform);
        game.setPrice(price);
    }
}