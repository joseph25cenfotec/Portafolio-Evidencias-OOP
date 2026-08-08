package model.employee;

import model.game.Game;
import model.game.GestorGame;
import model.User;

public class Employee extends User {

    private String username;
    private String password;
    private String role;

    // Constructor para registrar un empleado nuevo (id se asigna luego, tras insertar en BD)
    public Employee(String name, String username, String password, String role) {
        super(name);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor de hidratación: reconstruye un empleado ya existente en la BD
    public Employee(int id, String name, String username, String password, String role) {
        super(id, name);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void addGame(Game game) throws Exception {
        GestorGame.agregarGame(game.getTitle(), game.getPlatform(), game.getPrice());
    }

    public void deleteGame(String title) throws Exception {
        Game game = GestorGame.buscarGamePorTitulo(title);
        if (game != null) {
            GestorGame.eliminarGame(game.getId());
        }
    }

    public void updateGame(Game game, String title, String platform, double price) throws Exception {
        GestorGame.actualizarGame(game.getId(), title, platform, price);
    }
}