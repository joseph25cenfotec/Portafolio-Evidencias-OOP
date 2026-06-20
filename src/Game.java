import static utils.IdGenerator.generateUUID;

public class Game {

    private String id;
    private String title;
    private String platform;
    private double price;

    public Game(String title, String platform, double price) {
        this.id = generateUUID();
        this.title = title;
        this.platform = platform;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlatform() {
        return platform;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}