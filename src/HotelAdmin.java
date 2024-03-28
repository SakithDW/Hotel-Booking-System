import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class HotelAdmin extends Administrator{
    private Hotel hotel;

    static Scanner input = new Scanner(System.in);

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

    public void setPriceMap(){
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
