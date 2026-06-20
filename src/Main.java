void main() {
    Scanner scanner = new Scanner(System.in);

    IO.println("|| Sistema de Renta de Videojuegos ||");
    IO.println("--------------------------------------");

    IO.println("Ingrese el nombre de la tienda:");
    String storeName = scanner.nextLine();

    GameStore store = new GameStore(storeName);

    Employee employee = new Employee("Johan", "Administrador");

    while (true) {

        IO.println("\n=== MENÚ PRINCIPAL ===");
        IO.println("1. Cliente");
        IO.println("2. Empleado");
        IO.println("3. Salir");
        IO.print("Introduzca una opción: ");

        int mainOption = Integer.parseInt(scanner.nextLine());

        switch (mainOption) {

            case 1:
                while (true) {
                    IO.println("\n=== MENÚ CLIENTE ===");
                    IO.println("1. Rentar videojuego");
                    IO.println("2. Devolver videojuego");
                    IO.println("3. Ver videojuegos");
                    IO.println("4. Volver");
                    IO.print("Introduzca una opción: ");

                    int customerOption = Integer.parseInt(scanner.nextLine());

                    switch (customerOption) {
                        case 1:

                            IO.println("Ingrese nombre del cliente:");
                            String customerName = scanner.nextLine();

                            IO.println("Ingrese título del videojuego:");
                            String title = scanner.nextLine();

                            Game game = store.findGameByTitle(title);

                            if (game == null) {
                                IO.println("Videojuego no encontrado.");
                            } else if (store.isRented(title, customerName)) {
                                IO.println("Ese videojuego ya está rentado.");
                            } else {
                                Customer customer = new Customer(customerName);
                                customer.rentGame(store, game);
                                IO.println("Videojuego rentado: " + game.getTitle());
                                IO.println("Precio: " + game.getPrice());
                            }
                            break;

                        case 2:
                            IO.println("Ingrese nombre del cliente:");
                            customerName = scanner.nextLine();
                            IO.println("Ingrese título del videojuego a devolver:");
                            title = scanner.nextLine();
                            game = store.findGameByTitle(title);
                            if (game == null) {
                                IO.println("Videojuego no encontrado.");
                            } else if (!store.isRented(title, customerName)) {
                                IO.println("Ese videojuego no está rentado actualmente.");
                            } else if (!store.isRented(customerName, title)) {

                                IO.println(
                                        "Ese videojuego no está rentado por ese cliente."
                                );

                            } else {
                                Customer customer = new Customer(customerName);
                                customer.returnGame(store, title);
                                IO.println("Videojuego devuelto: " + game.getTitle());
                            }
                            break;

                        case 3:
                            IO.println("\n=== VIDEOJUEGOS ===");
                            if (store.getGames().isEmpty()) {
                                IO.println("No se encontraron videojuegos");
                            } else {
                                for (Game g : store.getGames()) {
                                    IO.println("- " + g.getTitle() + " | " + g.getPlatform() + " | $" + g.getPrice());
                                }
                            }
                            break;

                        case 4:
                            break;

                        default:
                            IO.println("Opción inválida.");
                    }

                    if (customerOption == 4) {
                        break;
                    }
                }
                break;

            case 2:
                while (true) {
                    IO.println("\n=== MENÚ EMPLEADO ===");
                    IO.println("1. Agregar nuevo videojuego");
                    IO.println("2. Actualizar información de un videojuego");
                    IO.println("3. Eliminar un videojuego");
                    IO.println("4. Ver videojuegos");
                    IO.println("5. Ver rentas actuales");
                    IO.println("6. Volver");
                    IO.print("Introduzca una opción: ");

                    int employeeOption = Integer.parseInt(scanner.nextLine());

                    switch (employeeOption) {
                        case 1:
                            IO.println("Título:");
                            String title = scanner.nextLine();

                            IO.println("Plataforma:");
                            String platform = scanner.nextLine();

                            IO.println("Precio:");
                            double price = Double.parseDouble(scanner.nextLine());

                            employee.addGame(store, new Game(title, platform, price));

                            IO.println("Videojuego creado.");
                            break;

                        case 2:

                            IO.println("Ingrese título del videojuego a actualizar:");

                            title = scanner.nextLine();

                            Game game = store.findGameByTitle(title);

                            if (game == null) {
                                IO.println("No encontrado.");
                            } else {
                                IO.println("Nuevo título:");
                                String newTitle = scanner.nextLine();

                                IO.println("Nueva plataforma:");
                                String newPlatform = scanner.nextLine();

                                IO.println("Nuevo precio:");
                                double newPrice = Double.parseDouble(scanner.nextLine());

                                employee.updateGame(game, newTitle, newPlatform, newPrice);

                                IO.println("Videojuego actualizado.");
                            }
                            break;

                        case 3:
                            IO.println("Ingrese título del videojuego a eliminar:");
                            title = scanner.nextLine();
                            game = store.findGameByTitle(title);

                            if (game == null) {
                                IO.println("Videojuego no encontrado.");
                            } else {
                                IO.println("¿Seguro que desea eliminar \"" + game.getTitle() + "\"? (si/no)");
                                String confirmation = scanner.nextLine();
                                if (confirmation.equalsIgnoreCase("si")) {
                                    employee.deleteGame(store, title);
                                    IO.println("Videojuego eliminado.");
                                } else {
                                    IO.println("Operación cancelada.");
                                }
                            }
                            break;

                        case 4:
                            IO.println("\n=== VIDEOJUEGOS ===");
                            if (store.getGames().isEmpty()) {
                                IO.println("No hay videojuegos registrados.");
                            } else {
                                for (Game g : store.getGames()) {
                                    IO.println("- " + g.getTitle() + " | " + g.getPlatform() + " | $" + g.getPrice());
                                }
                            }
                            break;

                        case 5:
                            IO.println("\n=== RENTAS ACTUALES ===");
                            if (store.getRentals().isEmpty()) {
                                IO.println("No hay videojuegos rentados actualmente.");
                            } else {
                                for (Rental rental : store.getRentals()) {
                                    IO.println("- " + rental);
                                }
                            }
                            break;

                        case 6:
                            break;

                        default:
                            IO.println("Opción inválida.");
                    }

                    if (employeeOption == 6) {
                        break;
                    }
                }
                break;

            case 3:
                IO.println("Sistema cerrado.");
                return;
            default:
                IO.println("Opción inválida.");
        }
    }
}