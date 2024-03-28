public abstract class Administrator {
    private String adminID;
    private String name;
    private String password;
    private String email;

    public Administrator(String adminID, String name, String password, String email) {
        this.adminID = adminID;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void generateID(){
        if(this instanceof HotelAdmin){
            Validation.IDGenerator("H",5);
        }
        if (this instanceof SystemAdmin){
            Validation.IDGenerator("S",5);
        }
    }

}
