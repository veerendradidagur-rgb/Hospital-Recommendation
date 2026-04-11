package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import dao.*;
import model.*;

public class HotelManagementGUI extends JFrame {
    
    // DAOs
    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;
    
    // Main components
    private JTabbedPane tabbedPane;
    private JPanel hotelPanel, roomPanel, guestPanel, reservationPanel;
    
    // Hotel components
    private JTextField hotelNameField, hotelLocationField, hotelAmenitiesField;
    private JTable hotelTable;
    private DefaultTableModel hotelTableModel;
    
    // Room components
    private JTextField roomNumberField, roomPriceField;
    private JComboBox<String> roomTypeCombo, hotelCombo;
    private JCheckBox availableCheckBox;
    private JTable roomTable;
    private DefaultTableModel roomTableModel;
    
    // Guest components
    private JTextField guestNameField, guestEmailField, guestPhoneField, guestAddressField;
    private JTable guestTable;
    private DefaultTableModel guestTableModel;
    
    // Reservation components
    private JComboBox<String> guestCombo, roomCombo;
    private JTextField checkInField, checkOutField, totalPriceField;
    private JComboBox<String> statusCombo;
    private JTable reservationTable;
    private DefaultTableModel reservationTableModel;
    
    public HotelManagementGUI() {
        // Initialize DAOs
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
        guestDAO = new GuestDAO();
        reservationDAO = new ReservationDAO();
        
        // Initialize GUI
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Hotel Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Create panels
        createHotelPanel();
        createRoomPanel();
        createGuestPanel();
        createReservationPanel();
        
        // Add panels to tabbed pane
        tabbedPane.addTab("Hotels", hotelPanel);
        tabbedPane.addTab("Rooms", roomPanel);
        tabbedPane.addTab("Guests", guestPanel);
        tabbedPane.addTab("Reservations", reservationPanel);
        
        // Add tabbed pane to frame
        add(tabbedPane);
        
        // Load initial data
        refreshHotelTable();
        refreshRoomTable();
        refreshGuestTable();
        refreshReservationTable();
    }
    
    private void createHotelPanel() {
        hotelPanel = new JPanel(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Hotel Name:"), gbc);
        gbc.gridx = 1;
        hotelNameField = new JTextField(20);
        inputPanel.add(hotelNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        hotelLocationField = new JTextField(20);
        inputPanel.add(hotelLocationField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Amenities:"), gbc);
        gbc.gridx = 1;
        hotelAmenitiesField = new JTextField(20);
        inputPanel.add(hotelAmenitiesField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addHotelBtn = new JButton("Add Hotel");
        JButton updateHotelBtn = new JButton("Update Hotel");
        JButton deleteHotelBtn = new JButton("Delete Hotel");
        
        addHotelBtn.addActionListener(e -> addHotel());
        updateHotelBtn.addActionListener(e -> updateHotel());
        deleteHotelBtn.addActionListener(e -> deleteHotel());
        
        buttonPanel.add(addHotelBtn);
        buttonPanel.add(updateHotelBtn);
        buttonPanel.add(deleteHotelBtn);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        hotelPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Table
        hotelTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Location", "Amenities"}, 0);
        hotelTable = new JTable(hotelTableModel);
        hotelPanel.add(new JScrollPane(hotelTable), BorderLayout.CENTER);
    }
    
    private void createRoomPanel() {
        roomPanel = new JPanel(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Hotel:"), gbc);
        gbc.gridx = 1;
        hotelCombo = new JComboBox<>();
        inputPanel.add(hotelCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Room Number:"), gbc);
        gbc.gridx = 1;
        roomNumberField = new JTextField(20);
        inputPanel.add(roomNumberField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        roomTypeCombo = new JComboBox<>(new String[]{"SINGLE", "DOUBLE", "SUITE"});
        inputPanel.add(roomTypeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Price per Night:"), gbc);
        gbc.gridx = 1;
        roomPriceField = new JTextField(20);
        inputPanel.add(roomPriceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("Available:"), gbc);
        gbc.gridx = 1;
        availableCheckBox = new JCheckBox();
        inputPanel.add(availableCheckBox, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addRoomBtn = new JButton("Add Room");
        JButton updateRoomBtn = new JButton("Update Room");
        JButton deleteRoomBtn = new JButton("Delete Room");
        
        addRoomBtn.addActionListener(e -> addRoom());
        updateRoomBtn.addActionListener(e -> updateRoom());
        deleteRoomBtn.addActionListener(e -> deleteRoom());
        
        buttonPanel.add(addRoomBtn);
        buttonPanel.add(updateRoomBtn);
        buttonPanel.add(deleteRoomBtn);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        roomPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Table
        roomTableModel = new DefaultTableModel(new String[]{"ID", "Hotel ID", "Room Number", "Type", "Price", "Available"}, 0);
        roomTable = new JTable(roomTableModel);
        roomPanel.add(new JScrollPane(roomTable), BorderLayout.CENTER);
    }
    
    private void createGuestPanel() {
        guestPanel = new JPanel(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        guestNameField = new JTextField(20);
        inputPanel.add(guestNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        guestEmailField = new JTextField(20);
        inputPanel.add(guestEmailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        guestPhoneField = new JTextField(20);
        inputPanel.add(guestPhoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        guestAddressField = new JTextField(20);
        inputPanel.add(guestAddressField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addGuestBtn = new JButton("Add Guest");
        JButton updateGuestBtn = new JButton("Update Guest");
        JButton deleteGuestBtn = new JButton("Delete Guest");
        
        addGuestBtn.addActionListener(e -> addGuest());
        updateGuestBtn.addActionListener(e -> updateGuest());
        deleteGuestBtn.addActionListener(e -> deleteGuest());
        
        buttonPanel.add(addGuestBtn);
        buttonPanel.add(updateGuestBtn);
        buttonPanel.add(deleteGuestBtn);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        guestPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Table
        guestTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0);
        guestTable = new JTable(guestTableModel);
        guestPanel.add(new JScrollPane(guestTable), BorderLayout.CENTER);
    }
    
    private void createReservationPanel() {
        reservationPanel = new JPanel(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Guest:"), gbc);
        gbc.gridx = 1;
        guestCombo = new JComboBox<>();
        inputPanel.add(guestCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Room:"), gbc);
        gbc.gridx = 1;
        roomCombo = new JComboBox<>();
        inputPanel.add(roomCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Check-in Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        checkInField = new JTextField(20);
        inputPanel.add(checkInField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Check-out Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        checkOutField = new JTextField(20);
        inputPanel.add(checkOutField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("Total Price:"), gbc);
        gbc.gridx = 1;
        totalPriceField = new JTextField(20);
        inputPanel.add(totalPriceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        statusCombo = new JComboBox<>(new String[]{"CONFIRMED", "CANCELLED", "COMPLETED"});
        inputPanel.add(statusCombo, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addReservationBtn = new JButton("Add Reservation");
        JButton updateReservationBtn = new JButton("Update Reservation");
        JButton deleteReservationBtn = new JButton("Delete Reservation");
        
        addReservationBtn.addActionListener(e -> addReservation());
        updateReservationBtn.addActionListener(e -> updateReservation());
        deleteReservationBtn.addActionListener(e -> deleteReservation());
        
        buttonPanel.add(addReservationBtn);
        buttonPanel.add(updateReservationBtn);
        buttonPanel.add(deleteReservationBtn);
        
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        reservationPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Table
        reservationTableModel = new DefaultTableModel(new String[]{"ID", "Guest ID", "Room ID", "Check-in", "Check-out", "Price", "Status"}, 0);
        reservationTable = new JTable(reservationTableModel);
        reservationPanel.add(new JScrollPane(reservationTable), BorderLayout.CENTER);
    }
    
    // Hotel methods
    private void addHotel() {
        String name = hotelNameField.getText();
        String location = hotelLocationField.getText();
        String amenities = hotelAmenitiesField.getText();
        
        if (name.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Location are required!");
            return;
        }
        
        Hotel hotel = new Hotel(name, location, amenities);
        hotelDAO.addHotel(hotel);
        refreshHotelTable();
        clearHotelFields();
    }
    
    private void updateHotel() {
        int selectedRow = hotelTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a hotel to update!");
            return;
        }
        
        int hotelId = (int) hotelTable.getValueAt(selectedRow, 0);
        String location = hotelLocationField.getText();
        
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location is required!");
            return;
        }
        
        hotelDAO.updateHotelLocation(hotelId, location);
        refreshHotelTable();
    }
    
    private void deleteHotel() {
        int selectedRow = hotelTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a hotel to delete!");
            return;
        }
        
        int hotelId = (int) hotelTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this hotel?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            hotelDAO.deleteHotel(hotelId);
            refreshHotelTable();
        }
    }
    
    private void refreshHotelTable() {
        hotelTableModel.setRowCount(0);
        try {
            List<Hotel> hotels = hotelDAO.getHotels();
            for (Hotel hotel : hotels) {
                hotelTableModel.addRow(new Object[]{
                    hotel.getHotelId(),
                    hotel.getName(),
                    hotel.getLocation(),
                    hotel.getAmenities()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading hotels: " + e.getMessage());
        }
        refreshHotelCombo();
        refreshRoomCombo();
    }
    
    private void refreshHotelCombo() {
        hotelCombo.removeAllItems();
        hotelCombo.addItem("Select Hotel");
        try {
            List<Hotel> hotels = hotelDAO.getHotels();
            for (Hotel hotel : hotels) {
                hotelCombo.addItem(hotel.getName() + " (ID: " + hotel.getHotelId() + ")");
            }
        } catch (Exception e) {
            System.err.println("Error loading hotel combo: " + e.getMessage());
        }
    }
    
    private void clearHotelFields() {
        hotelNameField.setText("");
        hotelLocationField.setText("");
        hotelAmenitiesField.setText("");
    }
    
    // Room methods
    private void addRoom() {
        try {
            int hotelIndex = hotelCombo.getSelectedIndex();
            if (hotelIndex <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a hotel!");
                return;
            }
            
            String roomNumber = roomNumberField.getText();
            String type = (String) roomTypeCombo.getSelectedItem();
            double price = Double.parseDouble(roomPriceField.getText());
            boolean available = availableCheckBox.isSelected();
            
            if (roomNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Room number is required!");
                return;
            }
            
            // Note: This would need to get actual hotel ID from combo
            Room room = new Room(1, roomNumber, type, price, available);
            roomDAO.addRoom(room);
            refreshRoomTable();
            clearRoomFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price format!");
        }
    }
    
    private void updateRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room to update!");
            return;
        }
        
        try {
            int roomId = (int) roomTable.getValueAt(selectedRow, 0);
            double price = Double.parseDouble(roomPriceField.getText());
            
            roomDAO.updateRoomPrice(roomId, price);
            refreshRoomTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price format!");
        }
    }
    
    private void deleteRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room to delete!");
            return;
        }
        
        int roomId = (int) roomTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this room?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            roomDAO.deleteRoom(roomId);
            refreshRoomTable();
        }
    }
    
    private void refreshRoomTable() {
        roomTableModel.setRowCount(0);
        try {
            List<Room> rooms = roomDAO.getRooms();
            for (Room room : rooms) {
                roomTableModel.addRow(new Object[]{
                    room.getRoomId(),
                    room.getHotelId(),
                    room.getRoomNumber(),
                    room.getType(),
                    room.getPricePerNight(),
                    room.isAvailable()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
        refreshRoomCombo();
    }
    
    private void refreshRoomCombo() {
        roomCombo.removeAllItems();
        roomCombo.addItem("Select Room");
        try {
            List<Room> rooms = roomDAO.getRooms();
            for (Room room : rooms) {
                if (room.isAvailable()) {
                    roomCombo.addItem(room.getRoomNumber() + " (ID: " + room.getRoomId() + ")");
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading room combo: " + e.getMessage());
        }
    }
    
    private void clearRoomFields() {
        roomNumberField.setText("");
        roomPriceField.setText("");
        availableCheckBox.setSelected(false);
    }
    
    // Guest methods
    private void addGuest() {
        String name = guestNameField.getText();
        String email = guestEmailField.getText();
        String phone = guestPhoneField.getText();
        String address = guestAddressField.getText();
        
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name, Email, and Phone are required!");
            return;
        }
        
        Guest guest = new Guest(name, email, phone, address);
        guestDAO.addGuest(guest);
        refreshGuestTable();
        clearGuestFields();
    }
    
    private void updateGuest() {
        int selectedRow = guestTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a guest to update!");
            return;
        }
        
        int guestId = (int) guestTable.getValueAt(selectedRow, 0);
        String email = guestEmailField.getText();
        String phone = guestPhoneField.getText();
        
        if (!email.isEmpty()) {
            guestDAO.updateGuestEmail(guestId, email);
        }
        if (!phone.isEmpty()) {
            guestDAO.updateGuestPhone(guestId, phone);
        }
        
        refreshGuestTable();
    }
    
    private void deleteGuest() {
        int selectedRow = guestTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a guest to delete!");
            return;
        }
        
        int guestId = (int) guestTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this guest?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            guestDAO.deleteGuest(guestId);
            refreshGuestTable();
        }
    }
    
    private void refreshGuestTable() {
        guestTableModel.setRowCount(0);
        try {
            List<Guest> guests = guestDAO.getGuests();
            for (Guest guest : guests) {
                guestTableModel.addRow(new Object[]{
                    guest.getGuestId(),
                    guest.getName(),
                    guest.getEmail(),
                    guest.getPhone(),
                    guest.getAddress()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading guests: " + e.getMessage());
        }
        refreshGuestCombo();
    }
    
    private void refreshGuestCombo() {
        guestCombo.removeAllItems();
        guestCombo.addItem("Select Guest");
        try {
            List<Guest> guests = guestDAO.getGuests();
            for (Guest guest : guests) {
                guestCombo.addItem(guest.getName() + " (ID: " + guest.getGuestId() + ")");
            }
        } catch (Exception e) {
            System.err.println("Error loading guest combo: " + e.getMessage());
        }
    }
    
    private void clearGuestFields() {
        guestNameField.setText("");
        guestEmailField.setText("");
        guestPhoneField.setText("");
        guestAddressField.setText("");
    }
    
    // Reservation methods
    private void addReservation() {
        try {
            int guestIndex = guestCombo.getSelectedIndex();
            int roomIndex = roomCombo.getSelectedIndex();
            
            if (guestIndex <= 0 || roomIndex <= 0) {
                JOptionPane.showMessageDialog(this, "Please select guest and room!");
                return;
            }
            
            String checkInStr = checkInField.getText();
            String checkOutStr = checkOutField.getText();
            double totalPrice = Double.parseDouble(totalPriceField.getText());
            String status = (String) statusCombo.getSelectedItem();
            
            if (checkInStr.isEmpty() || checkOutStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Check-in and Check-out dates are required!");
                return;
            }
            
            LocalDate checkIn = LocalDate.parse(checkInStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate checkOut = LocalDate.parse(checkOutStr, DateTimeFormatter.ISO_LOCAL_DATE);
            
            // Note: This would need to get actual guest and room IDs from combos
            Reservation reservation = new Reservation(1, 1, checkIn, checkOut, totalPrice, status);
            reservationDAO.addReservation(reservation);
            refreshReservationTable();
            clearReservationFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void updateReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a reservation to update!");
            return;
        }
        
        int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
        String status = (String) statusCombo.getSelectedItem();
        
        reservationDAO.updateReservationStatus(reservationId, status);
        refreshReservationTable();
    }
    
    private void deleteReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a reservation to delete!");
            return;
        }
        
        int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this reservation?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            reservationDAO.deleteReservation(reservationId);
            refreshReservationTable();
        }
    }
    
    private void refreshReservationTable() {
        reservationTableModel.setRowCount(0);
        try {
            List<Reservation> reservations = reservationDAO.getReservations();
            for (Reservation reservation : reservations) {
                reservationTableModel.addRow(new Object[]{
                    reservation.getReservationId(),
                    reservation.getGuestId(),
                    reservation.getRoomId(),
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate(),
                    reservation.getTotalPrice(),
                    reservation.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading reservations: " + e.getMessage());
        }
    }
    
    private void clearReservationFields() {
        checkInField.setText("");
        checkOutField.setText("");
        totalPriceField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HotelManagementGUI().setVisible(true);
        });
    }
}
