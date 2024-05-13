import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class HotelAdmin extends Administrator{

    Hotel hotel;

    public HotelAdmin(String adminID, String name, String password, String email,Hotel hotel) {
        super(adminID, name, password, email);
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
