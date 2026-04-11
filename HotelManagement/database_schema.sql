-- Hotel Management System Database Schema
-- MySQL Database Script

-- Create database
CREATE DATABASE IF NOT EXISTS hospitality_db;
USE hospitality_db;

-- Create Hotel table
CREATE TABLE hotel (
    hotel_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    amenities TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Guest table
CREATE TABLE guest (
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Room table
CREATE TABLE room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_id INT NOT NULL,
    room_number VARCHAR(10) NOT NULL,
    type ENUM('SINGLE', 'DOUBLE', 'SUITE') NOT NULL,
    price_per_night DECIMAL(10,2) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id) ON DELETE CASCADE,
    UNIQUE KEY (hotel_id, room_number)
);

-- Create Reservation table
CREATE TABLE reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    status ENUM('CONFIRMED', 'CANCELLED', 'COMPLETED') DEFAULT 'CONFIRMED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (guest_id) REFERENCES guest(guest_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE,
    CHECK (check_out_date > check_in_date)
);

-- Insert sample data for testing

-- Sample Hotels
INSERT INTO hotel (name, location, amenities) VALUES
('Grand Palace Hotel', 'Bangalore', 'WiFi, Pool, Parking, Spa, Restaurant'),
('City View Inn', 'Mumbai', 'WiFi, Parking, Breakfast, Gym'),
('Beach Resort', 'Goa', 'WiFi, Pool, Beach Access, Restaurant, Bar'),
('Mountain Lodge', 'Manali', 'WiFi, Parking, Fireplace, Restaurant'),
('Business Hotel', 'Delhi', 'WiFi, Parking, Conference Room, Gym');

-- Sample Guests
INSERT INTO guest (name, email, phone, address) VALUES
('John Smith', 'john.smith@email.com', '9876543210', '123 Main St, Bangalore'),
('Sarah Johnson', 'sarah.j@email.com', '8765432109', '456 Park Ave, Mumbai'),
('Michael Brown', 'michael.b@email.com', '7654321098', '789 Beach Rd, Goa'),
('Emily Davis', 'emily.d@email.com', '6543210987', '321 Mountain Path, Manali'),
('Robert Wilson', 'robert.w@email.com', '5432109876', '654 Business Blvd, Delhi');

-- Sample Rooms
INSERT INTO room (hotel_id, room_number, type, price_per_night, is_available) VALUES
-- Grand Palace Hotel rooms
(1, '101', 'SINGLE', 1500.00, TRUE),
(1, '102', 'SINGLE', 1500.00, TRUE),
(1, '201', 'DOUBLE', 2500.00, TRUE),
(1, '202', 'DOUBLE', 2500.00, TRUE),
(1, '301', 'SUITE', 4500.00, TRUE),

-- City View Inn rooms
(2, '101', 'SINGLE', 1200.00, TRUE),
(2, '102', 'DOUBLE', 2000.00, TRUE),
(2, '201', 'DOUBLE', 2000.00, TRUE),
(2, '301', 'SUITE', 3500.00, TRUE),

-- Beach Resort rooms
(3, '101', 'SINGLE', 1800.00, TRUE),
(3, '201', 'DOUBLE', 3000.00, TRUE),
(3, '301', 'SUITE', 5000.00, TRUE),

-- Mountain Lodge rooms
(4, '101', 'SINGLE', 1000.00, TRUE),
(4, '201', 'DOUBLE', 1800.00, TRUE),
(4, '301', 'SUITE', 2800.00, TRUE),

-- Business Hotel rooms
(5, '101', 'SINGLE', 1300.00, TRUE),
(5, '102', 'SINGLE', 1300.00, TRUE),
(5, '201', 'DOUBLE', 2200.00, TRUE),
(5, '301', 'SUITE', 4000.00, TRUE);

-- Sample Reservations
INSERT INTO reservation (guest_id, room_id, check_in_date, check_out_date, total_price, status) VALUES
(1, 1, '2024-01-15', '2024-01-17', 3000.00, 'COMPLETED'),
(2, 6, '2024-01-20', '2024-01-22', 2400.00, 'COMPLETED'),
(3, 11, '2024-02-10', '2024-02-12', 3600.00, 'CONFIRMED'),
(4, 14, '2024-02-15', '2024-02-18', 2700.00, 'CONFIRMED'),
(5, 16, '2024-03-01', '2024-03-03', 2600.00, 'CANCELLED');

-- Create indexes for better performance
CREATE INDEX idx_reservation_guest ON reservation(guest_id);
CREATE INDEX idx_reservation_room ON reservation(room_id);
CREATE INDEX idx_reservation_dates ON reservation(check_in_date, check_out_date);
CREATE INDEX idx_room_hotel ON room(hotel_id);
CREATE INDEX idx_room_available ON room(is_available);

-- Display sample data
SELECT 'Hotels:' as info;
SELECT * FROM hotel;

SELECT 'Guests:' as info;
SELECT * FROM guest;

SELECT 'Rooms:' as info;
SELECT * FROM room;

SELECT 'Reservations:' as info;
SELECT * FROM reservation;
