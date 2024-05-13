package rooms;

public abstract class Room {
    private String roomNo;
    private String capacity;
    private String description;
    private double price;
    private boolean available;
    public Room( String capacity, String description, double price){;
        this.capacity = capacity;
        this.description = description;
        this.price = price;
    }

    public Room(String roomNo, String capacity, String description, double price,
                boolean available) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.description = description;
        this.price = price;
        this.available = available;
    }




    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }



}
