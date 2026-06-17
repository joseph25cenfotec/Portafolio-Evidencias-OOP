void main() {

    Scanner scanner = new Scanner(System.in);

    IO.println("|| Sistema de Renta de Videojuegos ||");
    IO.println("--------------------------------------");

    IO.println("Ingrese el nombre de la tienda:");
    String storeName = scanner.nextLine();

    GameStore store = new GameStore(storeName);

    while (true) {

        IO.println("\n=== MENÚ PRINCIPAL ===");
        IO.println("1. Rentar videojuego");
        IO.println("2. Devolver videojuego");
        IO.println("3. Ver videojuegos");
        IO.println("4. Crear videojuego");
        IO.println("5. Actualizar videojuego");
        IO.println("6. Eliminar videojuego");
        IO.println("7. Ver rentas actuales");
        IO.println("8. Salir");

        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {

            IO.println("Ingrese nombre del cliente:");
            String name = scanner.nextLine();

            Customer customer = new Customer(name);

            IO.println("Ingrese título del videojuego:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                IO.println("Videojuego no encontrado.");
            } else if (store.isRented(title)) {
                IO.println("Ese videojuego ya está rentado.");
            } else {
                store.addRental(new Rental(game, customer));
                IO.println("Videojuego rentado: " + game.getTitle());
                IO.println("Precio: " + game.getPrice());
            }
        }

        if (option == 2) {

            IO.println("Ingrese título del videojuego a devolver:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                IO.println("Videojuego no encontrado.");
            } else if (!store.isRented(title)) {
                IO.println("Ese videojuego no está rentado actualmente.");
            } else {
                store.removeRental(title);
                IO.println("Videojuego devuelto: " + game.getTitle());
            }
        }

        if (option == 3) {

            IO.println("Lista de videojuegos:");

            for (Game g : store.getGames()) {
                IO.println("- " + g.getTitle() +
                        " | " + g.getPlatform() +
                        " | $" + g.getPrice());
            }
        }

        if (option == 4) {

            IO.println("Título:");
            String title = scanner.nextLine();

            IO.println("Plataforma:");
            String platform = scanner.nextLine();

            IO.println("Precio:");
            double price = Double.parseDouble(scanner.nextLine());

            store.addGame(new Game(title, platform, price));

            IO.println("Videojuego creado.");
        }

        if (option == 5) {

            IO.println("Ingrese título del videojuego a actualizar:");
            String title = scanner.nextLine();

            Game game = store.findGameByTitle(title);

            if (game == null) {
                IO.println("No encontrado.");
            } else {

                IO.println("Nuevo título:");
                game.setTitle(scanner.nextLine());

                IO.println("Nueva plataforma:");
                game.setPlatform(scanner.nextLine());

                IO.println("Nuevo precio:");
                game.setPrice(Double.parseDouble(scanner.nextLine()));

                IO.println("Videojuego actualizado.");
            }
        }

        if (option == 6) {

            IO.println("Ingrese título del videojuego a eliminar:");
            String title = scanner.nextLine();

            store.deleteGame(title);

            IO.println("Videojuego eliminado.");
        }

        if (option == 7) {

            IO.println("\n=== RENTAS ACTUALES ===");

            if (store.getRentals().isEmpty()) {
                IO.println("No hay videojuegos rentados actualmente.");
            } else {
                for (Rental renta : store.getRentals()) {
                    IO.println("- " + renta);
                }
            }
        }

        if (option == 8) {
            IO.println("Sistema cerrado.");
            break;
        }
    }

    scanner.close();
}