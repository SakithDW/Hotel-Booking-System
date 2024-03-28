import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Hotel> hotelList = new ArrayList<>();
    static ArrayList<Hotel>pendingList = new ArrayList<>();
    static ArrayList<Room> roomList = new ArrayList<>();
    static ArrayList<String> amenities = new ArrayList<>();
    public static void main(String[] args) {

    }
    public static void displaymenu(){

    }

    public void addHotel(){
        System.out.print("Enter the name of the hotel : ");
        String hotelName = input.next();
        ArrayList<String> IDList = new ArrayList<>();
        for(Hotel hotel: hotelList){
            IDList.add(hotel.getHotelID());
        }
        String hotelID = Validation.IDGenerator("H",7,IDList);
        System.out.print("Enter the address :");
        String address = input.next();
        Hotel hotel = new Hotel(hotelName,hotelID,address);
        hotel.setAmenities(enterAmenities());
        hotel.setRoomList(roomList);
        pendingList.add(hotel);
    }

    public void addRooms(Hotel hotel){
        String type = Validation.enterRoomCategory();
        StringBuilder combinedKey = new StringBuilder();
        String startingChar="";
        combinedKey.append(type);

        if(type.equalsIgnoreCase("Standard")){
            startingChar +="SR";
        } else if (type.equalsIgnoreCase("Deluxe")) {
            startingChar += "DR";
        } else if(type.equalsIgnoreCase("Premium")){
            startingChar += "PR";
        }
        ArrayList<String> IDList = new ArrayList<>();
        for(Room room: roomList){
            IDList.add(room.getRoomNo());
        }
        String capacity = Validation.enterCapacity();
        combinedKey.append(capacity);
        if(capacity.equalsIgnoreCase("Single")){
            startingChar += "S";
        }
        else if(capacity.equalsIgnoreCase("Double")){
            startingChar += "D";
        }
        else{
            startingChar+= "F";
        }
        String roomNo= Validation.IDGenerator(startingChar,5,IDList);

        Room roomInfo = hotel.getInfoMap().get(combinedKey.toString());
        if(type.equalsIgnoreCase("Standard")){
            Standard standard = new Standard(roomNo,roomInfo.getCapacity(),roomInfo.getDescription(),
                    roomInfo.getPrice(),true);
            hotel.getRoomList().add(standard);
        }
        else if(type.equalsIgnoreCase("Deluxe")){
            Deluxe deluxe = new Deluxe(roomNo,roomInfo.getCapacity(),roomInfo.getDescription(),
                    roomInfo.getPrice(),true);
            hotel.getRoomList().add(deluxe);
        }
        else {
            Premium premium = new Premium(roomNo,roomInfo.getCapacity(),roomInfo.getDescription(),
                    roomInfo.getPrice(),true);
            hotel.getRoomList().add(premium);
        }
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


    public void reviewAndAddHotel(){
        boolean on = true;
        while (on) {
            for (Hotel hotel : pendingList) {
                hotel.printHotelDetails();
                System.out.print("Do you want to add this hotel to the system?");
                String choice = input.next();
                if (choice.equalsIgnoreCase("yes")){
                    hotelList.add(hotel);
                    pendingList.remove(hotel);
                    System.out.print("Hotel added to the system successfully.");
                    break;
                }
            }
            System.out.println("""
                    
                    
                    Enter 'Yes' to continue reviewing process\s
                    Enter anything else to continue""");
            String choice = input.next();
            if(!choice.equalsIgnoreCase("yes")){
                on = false;
            }
        }
    }


}
