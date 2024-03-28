import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class HotelAdmin extends Administrator{
    static ArrayList<Room> roomList = new ArrayList<>();
    static ArrayList<String> amenities = new ArrayList<>();

    static Scanner input = new Scanner(System.in);

    public HotelAdmin(String adminID, String name, String password, String email) {
        super(adminID, name, password, email);
    }

    public void addHotel(){
        System.out.print("Enter the name of the hotel : ");
        String hotelName = input.next();
        String hotelID = Validation.IDGenerator("H",7);
        System.out.print("Enter the address :");
        String address = input.next();
        Hotel hotel = new Hotel(hotelName,hotelID,address);
        hotel.setAmenities(enterAmenities());
        hotel.setRoomList(roomList);
        SystemAdmin.pendingList.add(hotel);
    }
    public ArrayList<String> enterAmenities(){
        int count = 1;
        while(true){
            System.out.println("""
                    
                    Enter the amenities
                    Enter 'e' if all the entities are entered""");
            String amenityInput =input.next();
            amenities.add(STR."Amenity\{count} - \{amenityInput}");
            if(amenityInput.equalsIgnoreCase("e")){
                break;
            }
            count++;
        }
        return amenities;

    }
    public void addRooms(){
        String roomNo= Validation.IDGenerator("R",5);
        System.out.println("Add the description :");
        String description = input.next();
        System.out.print("Enter the price: ");
        double price = input.nextDouble();
        System.out.print("Enter the room type(Standard/Deluxe/Premium): ");
        String type = input.next();



    }

}
