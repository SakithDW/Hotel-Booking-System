public class Review {
    private String reviewID;
    private String content;
    private Customer customer;
    private Hotel hotel;

    public Review(String reviewID, String content, Customer customer, Hotel hotel) {
        this.reviewID = reviewID;
        this.content = content;
        this.customer = customer;
        this.hotel = hotel;
    }
    public Review(String reviewID, String content, Hotel hotel) {
        this.reviewID = reviewID;
        this.content = content;
        this.hotel = hotel;
    }
}
