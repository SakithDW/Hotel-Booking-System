import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static Scanner input = new Scanner(System.in);
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
    public static String IDGenerator(String startingChar, int IDLength, ArrayList<String> IDList) {
        Random random = new Random();
        String productID;

        do {
            StringBuilder idBuilder = new StringBuilder();
            idBuilder.append(startingChar).append(" - ");

            for (int i = 0; i < IDLength; i++) {
                idBuilder.append(random.nextInt(10)); // Append random digits between 0 to 9
            }

            productID = idBuilder.toString();
        } while (IDList.contains(productID));

        return productID;
    }

    public static String enterOptions(String prompt, String arg1, String arg2, String arg3){
        System.out.print(prompt);
        String category = input.next();
        while (true){
            if (category.equalsIgnoreCase(arg1) ||
                    category.equalsIgnoreCase(arg2) ||
                    category.equalsIgnoreCase(arg3)) {
                break;
            }
            else{
                System.out.println("Invalid input.");
            }
        }
        return category;
    }

//    public static String enterCapacity(){
//        System.out.print("Enter capacity(Single, Double, Family): ");
//        String capacity = input.next();
//        while (true){
//            if (capacity.equalsIgnoreCase("Single") ||
//                    capacity.equalsIgnoreCase("Double") ||
//                    capacity.equalsIgnoreCase("Family")) {
//                break;
//            }
//            else{
//                System.out.println("Invalid input.");
//            }
//        }
//        return capacity;
//
//    }


}
