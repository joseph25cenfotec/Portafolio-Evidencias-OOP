package model.Employee;

import model.Game.Game;
import model.Game.GestorGame;
import model.User;

public class Employee extends User {

    private String role;

    // Constructor para crear un empleado nuevo (id se asigna luego, tras insertar en BD)
    public Employee(String name, String role) {
        super(name);
        this.role = role;
    }

    // Constructor de hidratación: reconstruye un empleado ya existente en la BD
    public Employee(int id, String name, String role) {
        super(id, name);
        this.role = role;
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