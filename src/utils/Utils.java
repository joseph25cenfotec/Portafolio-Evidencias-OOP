package utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

public class Utils {
    // La clase Utils tiene la única finalidad de consultar un archivo ubicado en la ruta
    // almacenada en el String ruta y extraer de ahí la información de acceso a la base de datos.

    // Métodos
    public static String[] getProperties() throws Exception{

        String[] propiedades = new String[5];
        Properties propiedadesAccesoBD = new Properties();
        String ruta = "src/db.properties";

        try {
            propiedadesAccesoBD.load(new FileInputStream(ruta));
            propiedades[0] = propiedadesAccesoBD.getProperty("driver");
            propiedades[1] = propiedadesAccesoBD.getProperty("server");
            propiedades[2] = propiedadesAccesoBD.getProperty("dataBase");
            propiedades[3] = propiedadesAccesoBD.getProperty("user");
            propiedades[4] = propiedadesAccesoBD.getProperty("password");
            return propiedades;
        } catch(Exception e) {
            System.out.println("Se dio un error inesperado al cargar las credenciales de acceso a la base de datos.\n");
            throw e;
        }
    }

    public static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String escaparCaracteres(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.replace("'", "''");
    }
}
