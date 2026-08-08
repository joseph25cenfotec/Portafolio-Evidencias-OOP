package model.game;

import java.util.ArrayList;

public class GestorGame {

    public static int agregarGame(String title, String platform, double price) throws Exception {
        return DAOGame.insertarGame(title, platform, price);
    }

    public static ArrayList<Game> listarGames() throws Exception {
        return DAOGame.listarGames();
    }

    public static Game buscarGamePorId(int id) throws Exception {
        return DAOGame.buscarGamePorId(id);
    }

    public static Game buscarGamePorTitulo(String title) throws Exception {
        return DAOGame.buscarGamePorTitulo(title);
    }

    public static void actualizarGame(int id, String title, String platform, double price) throws Exception {
        DAOGame.actualizarGame(id, title, platform, price);
    }

    public static void eliminarGame(int id) throws Exception {
        DAOGame.eliminarGame(id);
    }
}