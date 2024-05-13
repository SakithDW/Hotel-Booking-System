package rooms;

public class Premium extends Room {

    public Premium(String roomNo, String capacity, String description, double price, boolean available) {
        super(roomNo, capacity, description, price, available);
    }
    public Premium( String capacity, String description, double price){
        super(capacity, description, price);
    }
}
