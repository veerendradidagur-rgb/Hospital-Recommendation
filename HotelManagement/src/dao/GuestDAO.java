package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Guest;
import util.DatabaseConnector;

public class GuestDAO {
    
    public void addGuest(Guest guest) {
        String sql = "INSERT INTO guest (name, email, phone, address) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.setString(3, guest.getPhone());
            ps.setString(4, guest.getAddress());
            
            ps.executeUpdate();
            System.out.println("Guest added successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Guest> getGuests() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guest";
        
        try (Connection con = DatabaseConnector.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Guest guest = new Guest(
                    rs.getInt("guest_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
                );
                guests.add(guest);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return guests;
    }
    
    public Guest getGuestById(int guestId) {
        String sql = "SELECT * FROM guest WHERE guest_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, guestId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Guest(
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address")
                    );
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void updateGuestEmail(int guestId, String email) {
        String sql = "UPDATE guest SET email = ? WHERE guest_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setInt(2, guestId);
            ps.executeUpdate();
            System.out.println("Guest email updated!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateGuestPhone(int guestId, String phone) {
        String sql = "UPDATE guest SET phone = ? WHERE guest_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, phone);
            ps.setInt(2, guestId);
            ps.executeUpdate();
            System.out.println("Guest phone updated!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteGuest(int guestId) {
        String sql = "DELETE FROM guest WHERE guest_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, guestId);
            ps.executeUpdate();
            System.out.println("Guest deleted!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
