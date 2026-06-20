import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static utils.IdGenerator.generateUUID;

public class Rental {

    private final String id;
    private Game game;
    private Customer customer;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    // Metodo para formatear fechas en formato dd-MM-yy (Día-Mes-Año)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Rental(Game game, Customer customer) {
        this.id = generateUUID();
        this.game = game;
        this.customer = customer;
        this.rentDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return customer.getName() + " | " + game.getTitle() + " | " + rentDate.format(formatter);
    }

    public String getId() { return id; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LocalDateTime getRentDate() { return rentDate; }
    public void setRentDate(LocalDateTime rentDate) { this.rentDate = rentDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
}