package model;

public class Room {

    private int roomId;         // AUTO_INCREMENT
    private int hotelId;        // Foreign key
    private String roomNumber;
    private String type;        // SINGLE, DOUBLE, SUITE
    private double pricePerNight;
    private boolean isAvailable;

    // Used when reading from DB
    public Room(int roomId, int hotelId, String roomNumber, String type, double pricePerNight, boolean isAvailable) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    // Used when inserting into DB
    public Room(int hotelId, String roomNumber, String type, double pricePerNight, boolean isAvailable) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
