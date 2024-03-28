import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SystemAdmin extends Administrator{
    static ArrayList<Hotel> hotelList = new ArrayList<>();
    static ArrayList<Hotel>pendingList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);


    public SystemAdmin(String adminID, String name, String password, String email) {
        super(adminID, name, password, email);
    }
    public void reviewAndAddHotel(){
        boolean on = true;
        while (on) {
            for (Hotel hotel : pendingList) {
                hotel.printHotelDetails();
                System.out.print("Do you want to add this hotel to the system?");
                String choice = input.next();
                if (choice.equalsIgnoreCase("yes")) {
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
