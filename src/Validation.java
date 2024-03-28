import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    static Scanner input = new Scanner(System.in);
    public static int intValidator(String prompt, int max, int min){
        while (true){
            try {
                System.out.print(prompt);
                int intValue = input.nextInt();
                if (intValue >= min || intValue <= max) {
                    return intValue;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid Input.");
                input.nextInt();
            }
        }
    }
    public static double doubleValidator(String prompt, double max, double min){
        while (true){
            try {
                System.out.print(prompt);
                int doubleValue = input.nextInt();
                if (doubleValue >= min || doubleValue <= max) {
                    return doubleValue;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid Input.");
                input.nextDouble();
            }
        }
    }
    public static String stringValidator(String prompt,String pattern){
        while (true){
            Pattern stringPattern = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
            System.out.println(prompt);
            String textInput = input.next();
            Matcher matcher = stringPattern.matcher(textInput);
            if (matcher.matches()) {
                return textInput;
            }
            System.out.println("Invalid Input. Please re-enter.");
        }
    }
    public static String IDGenerator(String startingChar,int IDLength){
        ArrayList<String> IDList = new ArrayList<>();
        for(Hotel hotel: SystemAdmin.hotelList){
            IDList.add(hotel.getHotelID());
        }
        StringBuilder productID = new StringBuilder();
        productID.append(startingChar).append(" - ");
        Random random = new Random();
        for(int i=0;i<IDLength;i++){
            productID.append(random.nextInt(10));
            if(!IDList.contains(productID.toString())){
                break;
            }
        }
        return productID.toString();
    }


}
