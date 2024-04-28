import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    static ArrayList<String>resIDList = new ArrayList<>();
    static ArrayList<String> amenities = new ArrayList<>();
    static ArrayList<HotelAdmin> hotelAdminList = new ArrayList<>();
    static ArrayList<String> hotelAdminIDList = new ArrayList<>();
    static ArrayList<Reservation> listOfReservations = new ArrayList<>();
    static String sysAdminUsername = "Jothipala";
    static String sysAdminPw = "1234567";

    public static void main(String[] args) {
        displayMenu();
    }
    public static void displayMenu() {
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
            System.out.print("Are you a registered user?(Y/N)");
            String yn = input.next();
            System.out.println();
            boolean run = true;
            while (run){
                if (yn.equalsIgnoreCase("y")) {
                    Customer customer = null;
                    System.out.println("Enter user email");
                    String email = input.next();
                    System.out.println("Enter password");
                    String password = input.next();

                    if (usersDetails.containsKey(email)) {
                        if (Objects.equals(password, usersDetails.get(email))) {
                            for (Hotel hotel : hotelList) {
                                System.out.println(STR."1. \{hotel.getHotelName()}:");
                                System.out.println("Amenities : ");
                                int count = 1;
                                for (String amenity : hotel.getAmenities()) {
                                    System.out.println(STR."\{count}. \{amenity}");
                                    count++;
                                }
                            }
                            for (Customer customer1 : customerList) {
                                if (Objects.equals(customer1.getCustomerEmail(), email)) {
                                    customer = customer1;
                                    break;
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
                            switch (selection) {
                                case 1 -> makeReservation(customer);
                                case 2 -> cancelReservation(customer);
                            }
                        } else {
                            System.out.println("Incorrect password.Please recheck.");
                        }

                    } else {
                        System.out.println("Invalid userID. Register before login.");
                        while (true) {
                            System.out.println("Do you want to register?(Y/N)");
                            String preference = input.next();
                            if (!preference.equalsIgnoreCase("Y") || preference.equalsIgnoreCase("N")) {
                                System.out.println("Invalid input.");
                            } else {
                                if (preference.equalsIgnoreCase("Y")) {
                                    userRegistration();
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    run=false;
                } else if(yn.equalsIgnoreCase("n")) {
                    System.out.println("Customer Registration");
                    userRegistration();
                    run=false;
                }
                else {
                    System.out.println("Invalid input. please enter Y/N.");
                }
            }


        } else if (choice==2) {
            System.out.println("WELCOME TO Hotel Admin PORTAL OF NEMO");
            System.out.println();
            System.out.println("Have you registered the hotel?(Y/N)");
            String ch = input.next();
            boolean runner =true;
            while (runner){
                if (ch.equalsIgnoreCase("y")) {
                    System.out.println("Enter user ID");
                    String userID = input.next();
                    System.out.println("Enter password");
                    String password = input.next();
                    Hotel hotel = (getHotelFromAdminID(userID));


                    if (hotelAdminDetails.containsKey(userID)) {
                        boolean run = true;
                        if (Objects.equals(password, hotelAdminDetails.get(userID))) {
                            while (run) {
                                System.out.println("""
                                        1. Add rooms
                                        2. Remove rooms
                                        3. Set prices
                                        4. Show Reservation Info
                                        0. Exit
                                                                    
                                        Enter the choice:
                                        """);
                                int selection = Validation.intValidator("Enter the choice", 4, 0);
                                switch (selection) {
                                    case 0 -> run = false;
                                    case 1 -> addRooms(hotel);

                                    case 2 -> removeRoom(hotel);

                                    case 3 -> setPriceMap(hotel);

                                }
                            }
                        } else {
                            System.out.println("incorrect Password.");
                        }
                    }
                    runner=false;
                } else if (ch.equalsIgnoreCase("n")) {
                    System.out.println("Hotel Registration");
                    registerHotel();
                    runner=false;
                } else {
                    System.out.println("Invalid input. please enter Y/N.");
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
        usersDetails.put(email,password);
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

    public static void makeReservation(Customer customer) {
        try {
            String reservationID = Validation.IDGenerator("re", 5, resIDList);

            System.out.println("Enter the hotel name:");
            String hotelName = input.next();

            System.out.println("Enter check-in date (YYYY-MM-DD): ");
            String date1 = input.next();
            Date checkInDate = parseDate(date1);

            System.out.println("Enter check-out date (YYYY-MM-DD): ");
            String date2 = input.next();
            Date checkOutDate = parseDate(date2);

            if (checkInDate == null || checkOutDate == null || checkOutDate.before(checkInDate)) {
                System.out.println("Invalid date format or check-out date is before check-in date.");
                return;
            }

            Hotel hotel = findHotelByName(hotelName);
            if (hotel == null) {
                System.out.println("Invalid hotel name.");
                return;
            }

            System.out.println("Enter room no: ");
            Room bookedRoom = findAvailableRoom(hotel);

            Reservation reservation = new Reservation(reservationID, customer, hotel, bookedRoom,
                    checkInDate, checkOutDate, bookedRoom.getPrice());
            listOfReservations.add(reservation);
            System.out.println("Reservation Successful.");
            System.out.println(STR."Your reservation id is \{reservationID}");
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

    private static Hotel findHotelByName(String name) {
        for (Hotel hotel : hotelList) {
            if (hotel.getHotelName().equalsIgnoreCase(name)) {
                return hotel;
            }
        }
        return null;
    }

    private static Room findAvailableRoom(Hotel hotel) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String roomNo = input.next();
            for (Room room : hotel.getRoomList()) {
                if (room.getRoomNo().equals(roomNo)) {
                    if (room.isAvailable()) {
                        return room;
                    } else {
                        System.out.println("This room is already booked.");
                        break;
                    }
                }
            }
            System.out.println("Invalid room no or room is not available. Enter again:");
        }
    }
    public  static void cancelReservation(Customer customer){
        System.out.println("Enter the reservation ID");
        String resID = input.next();
        if(!resIDList.contains(resID)){
            System.out.println("Invalid reservation ID");
        }
        else {
            for (Reservation reservation : listOfReservations) {
                if (Objects.equals(reservation.getReservationID(), resID)) {
                    if (Objects.equals(reservation.getCustomer().getCustomerID(), customer.getCustomerID())) {
                        boolean run = true;
                        while (run) {
                            System.out.println("Are you sure you want to cancel this reservation?(Y/N)");
                            String choice = input.next();
                            if (choice.equalsIgnoreCase("y")) {
                                listOfReservations.remove(reservation);
                                for (Hotel hotel : hotelList) {
                                    if (hotel == reservation.getHotel()) {
                                        for (Room room : hotel.getRoomList()) {
                                            if (Objects.equals(room.getRoomNo(), reservation.getRoom().getRoomNo())) {
                                                room.setAvailable(true);
                                            }
                                        }
                                    }
                                }
                                run = false;
                            } else if (choice.equalsIgnoreCase("n")) {
                                run = false;
                            } else {
                                System.out.println("Invalid Input.");
                            }
                        }
                    } else {
                        System.out.println("You are not allowed to cancel this reservation.");
                    }
                    break;
                }
            }
        }

    }

    //    public static void makeReservation(Customer customer) throws ParseException {
//        String reservationID = Validation.IDGenerator("re",5,resIDList);
//        System.out.println("Enter the hotel name");
//        String hotelName = input.next();
//
//        String date1 = (input.next());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkInDate = dateFormat.parse(date1);
//
//        System.out.print("Enter check-out date(YYYY-MM-DD): ");
//        String date2 = input.next();
//        Date checkOutDate = dateFormat.parse(date2);
//
//        Hotel hotel = null;
//        Room bookedRoom = null;
//        for(Hotel hotel1: hotelList){
//            if(Objects.equals(hotel1.getHotelName(), hotelName)){
//                hotel= hotel1;
//            }
//        }
//        if (hotel==null){
//            System.out.println("Invalid hotel name");
//        }
//        else {
//            System.out.print("Enter check-in date(YYYY-MM-DD): ");
//
//
//
//            System.out.println("Enter room no: ");
//            boolean run = true;
//            while (run){
//                String roomNo = input.next();
//
//                for (Room room : hotel.getRoomList()) {
//                    if (Objects.equals(room.getRoomNo(), roomNo)) {
//                        if (room.isAvailable()) {
//                            bookedRoom = room;
//                            run = false;
//                            break;
//                        } else {
//                            System.out.println("This room is already booked.");
//                        }
//                    }
//                }
//                if(bookedRoom==null){
//                    System.out.println("Invalid room no.");
//                }
//                else {
//                    Reservation reservation = new Reservation(reservationID,customer,hotel,bookedRoom,
//                            checkInDate,checkOutDate,bookedRoom.getPrice());
//                    listOfreservations.add(reservation);
//                    System.out.println("Reservation Successful.");
//
//                }
//            }
//        }
//
//
//
//    }


}
