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

    public void addRooms(){
        String type = Validation.enterRoomCategory();
        String startingChar;
        if(type.equals("standard")){
            startingChar="SR";
        } else if (type.equals("deluxe")) {
            startingChar= "DR";
        }
        else {
            startingChar = "PR";
        }
        ArrayList<String> IDList = new ArrayList<>();
        for(Room room: roomList){
            IDList.add(room.getRoomNo());
        }
        String roomNo= Validation.IDGenerator(startingChar,5,IDList);
        System.out.println("Add the description :");
        String description = input.next();
        System.out.print("Enter the price: ");
        double price = input.nextDouble();
        System.out.print("Enter the room type(Standard/Deluxe/Premium): ");

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

    public void setPriceMap(Hotel hotel){
        String category = Validation.enterRoomCategory();
        String capacity = Validation.enterCapacity();
        String combinedKey = category + capacity;
        if(hotel.getInfoMap().containsKey(combinedKey)){
            System.out.print("This category has already defined." +
                    "\nDo you wish to edit this field.");
            String choice = input.next().toLowerCase();
            if(!(choice.equals("yes")||choice.equals("no"))){
                System.out.println("Invalid input");
            }
            else {
                setMap(category,capacity,hotel);
                System.out.println("Field Updated Successfully.");
            }
        }
        else{
            setMap(category,capacity,hotel);
            System.out.println("New Field Added Successfully.");
        }
    }
    public void setMap(String category,String capacity,Hotel hotel){
        double price;
            if(category.equals("Standard")){
                if(capacity.equals("Single")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Standard standard = new Standard("Single",description,price);
                    hotel.getInfoMap().put(category,standard);
                }
                else if(capacity.equals("Double")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Standard standard = new Standard("Double",description,price);
                    hotel.getInfoMap().put(category,standard);
                }
                else{
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Standard standard = new Standard("Family",description,price);
                    hotel.getInfoMap().put(category,standard);
                }
            }
            else if(category.equals("Deluxe")){
                if(capacity.equals("Single")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Deluxe deluxe = new Deluxe("Single",description,price);
                    hotel.getInfoMap().put(category,deluxe);
                }
                else if(capacity.equals("Double")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Deluxe deluxe = new Deluxe("Double",description,price);
                    hotel.getInfoMap().put(category,deluxe);
                }
                else{
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Deluxe deluxe = new Deluxe("Family",description,price);
                    hotel.getInfoMap().put(category,deluxe);
                }
            }
            else{
                if(capacity.equals("Single")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Premium premium = new Premium("Single",description,price);
                    hotel.getInfoMap().put(category,premium);
                }
                else if(capacity.equals("Double")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Premium premium = new Premium("Double",description,price);
                    hotel.getInfoMap().put(category,premium);
                }
                else{
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Premium premium = new Premium("Family",description,price);
                    hotel.getInfoMap().put(category,premium);
                }
            }
    }
}
