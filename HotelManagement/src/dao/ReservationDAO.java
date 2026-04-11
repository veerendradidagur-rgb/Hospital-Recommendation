package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Reservation;
import util.DatabaseConnector;

public class ReservationDAO {
    
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (guest_id, room_id, check_in_date, check_out_date, total_price, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, reservation.getGuestId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            ps.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            ps.setDouble(5, reservation.getTotalPrice());
            ps.setString(6, reservation.getStatus());
            
            ps.executeUpdate();
            System.out.println("Reservation added successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        
        try (Connection con = DatabaseConnector.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Reservation reservation = new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getInt("guest_id"),
                    rs.getInt("room_id"),
                    rs.getDate("check_in_date").toLocalDate(),
                    rs.getDate("check_out_date").toLocalDate(),
                    rs.getDouble("total_price"),
                    rs.getString("status")
                );
                reservations.add(reservation);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reservations;
    }
    
    public List<Reservation> getReservationsByGuest(int guestId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE guest_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, guestId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getDouble("total_price"),
                        rs.getString("status")
                    );
                    reservations.add(reservation);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reservations;
    }
    
    public List<Reservation> getReservationsByRoom(int roomId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE room_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, roomId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getDouble("total_price"),
                        rs.getString("status")
                    );
                    reservations.add(reservation);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reservations;
    }
    
    public void updateReservationStatus(int reservationId, String status) {
        String sql = "UPDATE reservation SET status = ? WHERE reservation_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, reservationId);
            ps.executeUpdate();
            System.out.println("Reservation status updated!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, reservationId);
            ps.executeUpdate();
            System.out.println("Reservation deleted!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
