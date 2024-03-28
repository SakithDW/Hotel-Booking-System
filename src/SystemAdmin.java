import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SystemAdmin extends Administrator{


    static Scanner input = new Scanner(System.in);


    public SystemAdmin(String adminID, String name, String password, String email) {
        super(adminID, name, password, email);
    }


}
