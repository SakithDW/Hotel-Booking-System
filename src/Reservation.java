import java.util.Date;

public class Reservation {
    private String reservationID;
    private Customer customer;
    private Hotel hotel;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private double price;

    public Reservation(String reservationID, Customer customer, Hotel hotel,
                       Room room, Date checkInDate, Date checkOutDate, double price) {
        this.reservationID = reservationID;
        this.customer = customer;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.price = price;
    }

    public String getReservationID() {
        return reservationID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public double getPrice() {
        return price;
    }
}
