package model;

public class Hotel {

    private int hotelId;        // AUTO_INCREMENT
    private String name;
    private String location;
    private String amenities;

    // Used when reading from DB
    public Hotel(int hotelId, String name, String location, String amenities) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    // Used when inserting into DB
    public Hotel(String name, String location, String amenities) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getAmenities() { return amenities; }
}
