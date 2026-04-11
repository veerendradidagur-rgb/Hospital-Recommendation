package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Room;
import util.DatabaseConnector;

public class RoomDAO {
    
    public void addRoom(Room room) {
        String sql = "INSERT INTO room (hotel_id, room_number, type, price_per_night, is_available) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, room.getHotelId());
            ps.setString(2, room.getRoomNumber());
            ps.setString(3, room.getType());
            ps.setDouble(4, room.getPricePerNight());
            ps.setBoolean(5, room.isAvailable());
            
            ps.executeUpdate();
            System.out.println("Room added successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        
        try (Connection con = DatabaseConnector.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("room_id"),
                    rs.getInt("hotel_id"),
                    rs.getString("room_number"),
                    rs.getString("type"),
                    rs.getDouble("price_per_night"),
                    rs.getBoolean("is_available")
                );
                rooms.add(room);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rooms;
    }
    
    public List<Room> getRoomsByHotel(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE hotel_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, hotelId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room(
                        rs.getInt("room_id"),
                        rs.getInt("hotel_id"),
                        rs.getString("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price_per_night"),
                        rs.getBoolean("is_available")
                    );
                    rooms.add(room);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rooms;
    }
    
    public void updateRoomPrice(int roomId, double price) {
        String sql = "UPDATE room SET price_per_night = ? WHERE room_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDouble(1, price);
            ps.setInt(2, roomId);
            ps.executeUpdate();
            System.out.println("Room price updated!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateRoomAvailability(int roomId, boolean isAvailable) {
        String sql = "UPDATE room SET is_available = ? WHERE room_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBoolean(1, isAvailable);
            ps.setInt(2, roomId);
            ps.executeUpdate();
            System.out.println("Room availability updated!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteRoom(int roomId) {
        String sql = "DELETE FROM room WHERE room_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, roomId);
            ps.executeUpdate();
            System.out.println("Room deleted!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
