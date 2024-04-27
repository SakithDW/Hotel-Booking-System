import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class HotelAdmin extends Administrator{

    Hotel hotel;

    public HotelAdmin(String adminID, String name, String password, String email) {
        super(adminID, name, password, email);
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
