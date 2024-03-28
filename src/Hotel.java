import java.util.ArrayList;

import static java.lang.StringTemplate.STR;

public class Hotel {
    private String hotelName;
    private String hotelID;
    private String address;
    private ArrayList<String> amenities;
    private ArrayList<Room> roomList;

    public Hotel(String hotelName, String hotelID, String address) {
        this.hotelName = hotelName;
        this.hotelID = hotelID;
        this.address = address;
        this.amenities = new ArrayList<>();
        this.roomList = new ArrayList<>();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<String> amenities) {
        this.amenities = amenities;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    public void printHotelDetails(){
        System.out.println(STR."Hotel name: \{hotelName}");
        System.out.println(STR."Hotel ID: \{hotelID}");
        System.out.println(STR."Address: \{address}");
        System.out.println(" Amenities: ");
        for(String amenity: amenities){
            System.out.println(amenity);
        }
        System.out.println(STR."Room Count : \{roomList.size()}");
    }


}
