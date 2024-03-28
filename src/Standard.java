public class Standard extends Room{

    public Standard(String roomNo, String capacity, String description, double price, boolean available) {
        super(roomNo, capacity, description, price, available);
    }
    public Standard( String capacity, String description, double price){
        super(capacity, description, price);
    }
}
