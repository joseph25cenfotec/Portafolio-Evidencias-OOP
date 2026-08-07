package model;

public abstract class User {

    private int id;
    private final String name;

    // Constructor para crear un usuario nuevo. El id queda en 0 hasta que
    // el DAO lo inserte en la BD y le asigne el id real generado por MySQL.
    public User(String name) {
        this.name = name;
    }

    // Constructor de hidratación: reconstruye un usuario ya existente,
    // leído desde la base de datos, con su id real.
    protected User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    // Usado por el DAO justo después de insertar, para completar el id
    // que generó MySQL (AUTO_INCREMENT) en el objeto recién creado.
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}