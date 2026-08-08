package model.game;

public class Game {

    private int id;
    private String title;
    private String platform;
    private double price;

    // Constructor para crear un videojuego nuevo (id se asigna luego, tras insertar en BD)
    public Game(String title, String platform, double price) {
        this.title = title;
        this.platform = platform;
        this.price = price;
    }

    // Constructor de hidratación: reconstruye un videojuego ya existente en la BD
    public Game(int id, String title, String platform, double price) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    // Usado por el DAO justo después de insertar, para completar el id
    // que generó MySQL (AUTO_INCREMENT) en el objeto recién creado.
    public void setId(int id) {
        this.id = id;
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