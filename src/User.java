import static utils.IdGenerator.generateUUID;

public abstract class User {

    private String id;
    private String name;

    public User(String name) {
        this.id = generateUUID();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}