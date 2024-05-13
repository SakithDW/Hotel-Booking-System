import rooms.Room;

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
}
