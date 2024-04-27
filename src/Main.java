import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Hotel> hotelList = new ArrayList<>();
    static ArrayList<Hotel>pendingList = new ArrayList<>();
    static ArrayList<Room> roomList = new ArrayList<>();
    static HashMap <String,String> usersDetails = new HashMap<>();
    static HashMap <String,String> hotelAdminDetails = new HashMap<>();
    static ArrayList<Customer> customerList = new ArrayList<>();
    static ArrayList<String> customerIDList = new ArrayList<>();
    static ArrayList<String> hotelIDList = new ArrayList<>();
    static ArrayList<String> amenities = new ArrayList<>();
    static ArrayList<HotelAdmin> hotelAdminList = new ArrayList<>();
    static ArrayList<String> hotelAdminIDList = new ArrayList<>();
    static String sysAdminUsername = "Jothipala";
    static String sysAdminPw = "1234567";

    public static void main(String[] args) {
        displayMenu();
    }
    public static void displayMenu(){
        System.out.println("WELCOME TO NEMO");
        String prompt=("""
                Select Options:
                1. Customer Portal
                2. Hotel Admin Portal
                3. System Admin portal
                
                Enter the choice(1,2,3) :
                """);
        int choice = Validation.intValidator(prompt,3,1);
        if(choice==1){
            System.out.println("WELCOME TO CUSTOMER PORTAL OF NEMO");
            System.out.print("Have you registered your hotel?(Y/N)");
            String yn = input.next();
            System.out.println();
            if(yn.equalsIgnoreCase("y")){
                System.out.println("Enter user ID");
                String userID = input.next();
                System.out.println("Enter password");
                String password = input.next();

                if(usersDetails.containsKey(userID)){
                    if(Objects.equals(password, usersDetails.get(userID))){
                        for(Hotel hotel:hotelList){
                            System.out.println(STR."1. \{hotel.getHotelName()}:");
                            System.out.println("Amenities : ");
                            int count=1;
                            for (String  amenity: hotel.getAmenities()){
                                System.out.println(STR."\{count}. \{amenity}");
                                count++;
                            }
                        }
                        System.out.print("""
                            1. Make a reservation.
                            2. Cancel a reservation.
                            3. See available rooms.
                            4. Show reservations.
                            5. See history
                            0. Exit
                            
                            Enter the choice:
                            """);
                        int selection = Validation.intValidator("Enter the choice :", 5, 0);
//                    switch (selection){
//                        case 1 ->
//                    }
                    }else{
                        System.out.println("Incorrect password.Please recheck.");
                    }

                }
                else{
                    System.out.println("Invalid userID. Register before login.");
                    while(true){
                        System.out.println("Do you want to register?(Y/N)");
                        String preference = input.next();
                        if(!preference.equalsIgnoreCase("Y")||preference.equalsIgnoreCase("N")){
                            System.out.println("Invalid input.");
                        }
                        else {
                            if(preference.equalsIgnoreCase("Y")){
                                userRegistration();
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
            }else {
                System.out.println("Register Your hotel");
                registerHotel();
            }


        } else if (choice==2) {
            System.out.println("WELCOME TO Hotel Admin PORTAL OF NEMO");
            System.out.println();


            System.out.println("Enter user ID");
            String userID = input.next();
            System.out.println("Enter password");
            String password = input.next();
            Hotel hotel = (getHotelFromAdminID(userID));


            if(hotelAdminDetails.containsKey(userID)){
                boolean run = true;
                if(Objects.equals(password, hotelAdminDetails.get(userID))){
                    while (run){
                        System.out.println("""
                                1. Add rooms
                                2. Remove rooms
                                3. Set prices
                                4. Show Reservation Info
                                0. Exit
                                                            
                                Enter the choice:
                                """);
                        int selection = Validation.intValidator("Enter the choice", 4, 0);
                        switch (selection){
                            case 0 -> run= false;
                            case 1-> addRooms(hotel);

                            case 2-> removeRoom(hotel);

                            case 3 -> setPriceMap(hotel);

                        }
                    }
                }
                else {
                    System.out.println("incorrect Password.");
                }
            }


        }
        else {
            System.out.println("WELCOME TO SYSTEM ADMIN PORTAL\n");
            while (true){
                System.out.println("Enter your user name");
                String username = input.next();
                System.out.println("Enter your password");
                String password = input.next();

                if (Objects.equals(username, sysAdminUsername)) {
                        if (Objects.equals(password, sysAdminPw)) {
                            boolean run = true;
                            while (run) {
                                System.out.println("""
                                        1.Review and Add Hotels
                                        2.Review comments
                                        0.exit
                                        """);
                                int selection = Validation.intValidator("Enter the choice: ",2,0);
                                switch (selection){
                                    case(1)-> reviewAndAddHotel();
                                    case(0)-> run=false;
                                }
                            }
                            break;

                        }else {
                            System.out.println("Invalid password.please re-enter.");
                        }
                } else {
                    System.out.println("Invalid username. Please re-enter");
                }
            }


        }

    }
    public static Hotel getHotelFromAdminID(String ID){
        Hotel hotel = null;

        for(HotelAdmin hotelAdmin : hotelAdminList){
            if(Objects.equals(hotelAdmin.getAdminID(), ID)){
                hotel = hotelAdmin.getHotel();
                break;
            }
        }

        return hotel;
    }

    public static void removeRoom(Hotel hotel){
        System.out.print("Enter room no: ");
        String roomNo = input.next();
        hotel.getRoomList().removeIf(room -> Objects.equals(room.getRoomNo(), roomNo));
    }
    public static void  userRegistration(){
        System.out.print("Enter username: ");
        String username = input.next();
        String customerID = Validation.IDGenerator("C",5,customerIDList);
        System.out.print("Enter a strong password: ");
        String password = input.next();
        System.out.print("Enter email: ");
        String email = input.next();
        Customer customer = new Customer(customerID,username,password,email);
        customerList.add(customer);
        usersDetails.put(customerID,password);
        System.out.println("Registration Successful");
        System.out.println(STR."Your user ID is\{customerID}. \nPlease keep this in mind for later use.");

    }

    public static void registerHotel(){
        System.out.print("Enter the name of the hotel : ");
        String hotelName = input.next();
        String hotelID = Validation.IDGenerator("H",7,hotelIDList);
        System.out.print("Enter the address :");
        String address = input.next();
        Hotel hotel = new Hotel(hotelName,hotelID,address);
        hotel.setAmenities(enterAmenities());
        hotel.setRoomList(roomList);
        pendingList.add(hotel);
        System.out.println("HOTEL ADMIN DETAILS");
        System.out.print("Enter the username: ");
        String username = input.next();
        System.out.print("Enter the email: ");
        String email = input.next();
        System.out.print("Choose a password: ");
        String password = input.next();
        String ID = Validation.IDGenerator("HA",5,hotelAdminIDList);

        HotelAdmin hotelAdmin = new HotelAdmin(ID,username,password,email);
        hotelAdminDetails.put(username,password);
        hotel.setHotelAdmin(hotelAdmin);
        hotelAdmin.setHotel(hotel);
    }

    public static void addRooms(Hotel hotel){
        String category = Validation.enterOptions("Enter room category(Standard, Deluxe, Premium): ",
                "Standard","Deluxe","Premium");
        String capacity = Validation.enterOptions("Enter the room capacity(Single, Double, Family)",
                "Single","Double","Family");

        String key =category.toLowerCase()+capacity.toLowerCase();


        String startingChar;
        if(category.equalsIgnoreCase("standard")){
            startingChar="SR";
        } else if (category.equalsIgnoreCase("deluxe")) {
            startingChar= "DR";
        }else {
            startingChar = "PR";
        }
        ArrayList<String> IDList = new ArrayList<>();
        for(Room room: hotel.getRoomList()){
            IDList.add(room.getRoomNo());
        }
        String roomNo= Validation.IDGenerator(startingChar,5,IDList);

        System.out.print("Enter the price: ");
        if(!hotel.getInfoMap().containsKey(key)){
            setPriceMap(hotel);
        }
        String description = hotel.getInfoMap().get(key).getDescription();
        double price = hotel.getInfoMap().get(key).getPrice();

        if(category.equalsIgnoreCase("Standard")){
            Standard standard = new Standard(roomNo,capacity,description,price,true);
            hotel.getRoomList().add(standard);

        } else if(category.equalsIgnoreCase("Deluxe")){
            Deluxe deluxe = new Deluxe(roomNo,capacity,description,price,true);
            hotel.getRoomList().add(deluxe);

        }else if(category.equalsIgnoreCase("Premium")){
            Premium premium = new Premium(roomNo,capacity,description,price,true);
            hotel.getRoomList().add(premium);
        }
    }

    public static ArrayList<String> enterAmenities(){
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


    public static void reviewAndAddHotel(){
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

    public static void setPriceMap(Hotel hotel){
        String category = Validation.enterOptions("Enter room category(Standard, Deluxe, Premium): ",
                "Standard","Deluxe","Premium").toLowerCase();
        String capacity = Validation.enterOptions("Enter the room capacity(Single, Double, Family)",
                "Single","Double","Family").toLowerCase();
        String combinedKey = category + capacity;
        if(hotel.getInfoMap().containsKey(combinedKey)){
            Room value = hotel.getInfoMap().get(combinedKey);
            while (true){
                System.out.print("This category has already defined." +
                        "\nDo you wish to edit this field.");
                String choice = input.next().toLowerCase();
                if (!(choice.equals("yes") || choice.equals("no"))) {
                    System.out.println("Invalid input");
                } else {
                    if (choice.equalsIgnoreCase("yes")) {
                        setMap(category, capacity, hotel);
                        System.out.println("Field Updated Successfully.");
                    }
                    break;
                }
            }
        }
        else{
            setMap(category,capacity,hotel);
            System.out.println("New Field Added Successfully.");
        }
    }
    public static void setMap(String category,String capacity,Hotel hotel){
        double price;
        String key = capacity.toLowerCase()+capacity.toLowerCase();
            if(category.equalsIgnoreCase("Standard")){
                if(capacity.equalsIgnoreCase("Single")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Standard standard = new Standard("Single",description,price);
                    hotel.getInfoMap().put(key,standard);
                }
                else if(capacity.equalsIgnoreCase("Double")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Standard standard = new Standard("Double",description,price);
                    hotel.getInfoMap().put(key,standard);
                }
                else{
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Standard standard = new Standard("Family",description,price);
                    hotel.getInfoMap().put(key,standard);
                }
            }
            else if(category.equalsIgnoreCase("Deluxe")){
                if(capacity.equalsIgnoreCase("Single")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Deluxe deluxe = new Deluxe("Single",description,price);
                    hotel.getInfoMap().put(key,deluxe);
                }
                else if(capacity.equalsIgnoreCase("Double")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Deluxe deluxe = new Deluxe("Double",description,price);
                    hotel.getInfoMap().put(key,deluxe);
                }
                else{
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Deluxe deluxe = new Deluxe("Family",description,price);
                    hotel.getInfoMap().put(key,deluxe);
                }
            }
            else{
                if(capacity.equalsIgnoreCase("Single")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Premium premium = new Premium("Single",description,price);
                    hotel.getInfoMap().put(key,premium);
                }
                else if(capacity.equalsIgnoreCase("Double")){
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Premium premium = new Premium("Double",description,price);
                    hotel.getInfoMap().put(key,premium);
                }
                else{
                    System.out.println("Add a description: ");
                    String description = input.next();
                    price = Validation.doubleValidator("Enter the price",100000,1);
                    Premium premium = new Premium("Family",description,price);
                    hotel.getInfoMap().put(key,premium);
                }
            }
    }
    public static void makeReservation(){

    }
}
