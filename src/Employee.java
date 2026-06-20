import static utils.IdGenerator.generateUUID;

public class Employee {

    private String id;
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.id = generateUUID();
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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