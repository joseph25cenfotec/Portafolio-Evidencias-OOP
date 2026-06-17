import static utils.IdGenerator.generateUUID;

public class Customer {

    private final String id;
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
}