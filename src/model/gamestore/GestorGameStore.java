package model.gamestore;

public class GestorGameStore {

    // Retorna la tienda existente en BD, o null si todavía no se ha
    // creado ninguna (primera ejecución del sistema).
    public static GameStore obtenerStore() throws Exception {
        return DAOGameStore.obtenerStore();
    }

    // Crea la tienda con el nombre dado. Solo debe usarse una vez,
    // cuando obtenerStore() devolvió null.
    public static GameStore crearStore(String name) throws Exception {
        int id = DAOGameStore.insertarStore(name);
        return new GameStore(id, name);
    }

    public static void actualizarStore(int id, String name) throws Exception {
        DAOGameStore.actualizarStore(id, name);
    }
}