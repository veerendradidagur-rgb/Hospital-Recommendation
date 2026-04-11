package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Hotel;
import util.DatabaseConnector;

public class HotelDAO {
    
public void addHotel(Hotel hotel) {

    String sql = "INSERT INTO hotel (name, location, amenities) VALUES (?, ?, ?)";

    try (Connection con = DatabaseConnector.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, hotel.getName());
        ps.setString(2, hotel.getLocation());
        ps.setString(3, hotel.getAmenities());

        ps.executeUpdate();
        System.out.println("Hotel added successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public List<Hotel> getHotels() {
    List<Hotel> hotels = new ArrayList<>();
    String sql = "SELECT * FROM hotel";

    try (Connection con = DatabaseConnector.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Hotel hotel = new Hotel(
                rs.getInt("hotel_id"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getString("amenities")
            );
            hotels.add(hotel);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return hotels;
}
public void updateHotelLocation(int id, String location) {

    String sql = "UPDATE hotel SET location=? WHERE hotel_id=?";

    try (Connection con = DatabaseConnector.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, location);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Hotel updated!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void deleteHotel(int id) {

    String sql = "DELETE FROM hotel WHERE hotel_id=?";

    try (Connection con = DatabaseConnector.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Hotel deleted!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
