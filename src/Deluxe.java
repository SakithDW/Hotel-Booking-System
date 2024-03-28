public class Deluxe extends Room{

    public Deluxe(String roomNo, String capacity, String description, double price, boolean available) {
        super(roomNo, capacity, description, price, available);
    }
    public Deluxe( String capacity, String description, double price){
        super(capacity, description, price);
    }
}
