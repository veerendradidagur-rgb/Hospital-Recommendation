# Hotel Management System

A comprehensive Java-based Hotel Management System built with Swing GUI and MySQL database.

## Project Structure

```
HotelManagement/
├── src/
│   ├── Main.java                    # Main entry point
│   ├── model/                       # Entity classes
│   │   ├── Hotel.java
│   │   ├── Room.java
│   │   ├── Guest.java
│   │   └── Reservation.java
│   ├── dao/                         # Data Access Objects
│   │   ├── HotelDAO.java
│   │   ├── RoomDAO.java
│   │   ├── GuestDAO.java
│   │   └── ReservationDAO.java
│   ├── util/                        # Utility classes
│   │   └── DatabaseConnector.java
│   └── gui/                         # GUI components
│       └── HotelManagementGUI.java
├── lib/
│   └── mysql-connector-j-9.6.0.jar  # MySQL JDBC driver
├── database_schema.sql              # Database setup script
└── README.md                        # This file
```

## Features

- **Hotel Management**: Add, update, delete hotels
- **Room Management**: Add, update, delete rooms with availability tracking
- **Guest Management**: Add, update, delete guest information
- **Reservation System**: Create, update, cancel reservations
- **User-Friendly GUI**: Tabbed interface for easy navigation
- **Database Integration**: MySQL database with proper relationships

## Prerequisites

1. **Java Development Kit (JDK)** - Version 8 or higher
2. **MySQL Server** - Version 5.7 or higher
3. **IDE** - Eclipse, IntelliJ IDEA, or any Java IDE

## Setup Instructions

### 1. Database Setup

1. Install and start MySQL Server
2. Create the database and tables using the provided script:

```bash
mysql -u root -p < database_schema.sql
```

Or execute the SQL commands manually in MySQL Workbench or command line.

**Default Database Configuration:**
- Host: localhost:3307
- Database: hospitality_db
- Username: root
- Password: root123

### 2. Project Setup in Eclipse

1. Import the project into Eclipse:
   - File → Import → Existing Projects into Workspace
   - Select the HotelManagement folder

2. Configure the build path:
   - Right-click project → Build Path → Configure Build Path
   - Go to Libraries tab
   - Add External JARs → select `lib/mysql-connector-j-9.6.0.jar`

### 3. Update Database Connection (if needed)

If your MySQL configuration differs from defaults, update `DatabaseConnector.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3307/hospitality_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

## Running the Application

1. Make sure MySQL server is running
2. Run the `Main.java` file from your IDE
3. The Hotel Management GUI will launch

## Usage Guide

### Hotels Tab
- **Add Hotel**: Enter hotel details and click "Add Hotel"
- **Update Hotel**: Select a hotel from table, modify location, click "Update Hotel"
- **Delete Hotel**: Select a hotel and click "Delete Hotel"

### Rooms Tab
- **Add Room**: Select hotel, enter room details, click "Add Room"
- **Update Room**: Select a room, modify price, click "Update Room"
- **Delete Room**: Select a room and click "Delete Room"

### Guests Tab
- **Add Guest**: Enter guest details and click "Add Guest"
- **Update Guest**: Select a guest, modify email/phone, click "Update Guest"
- **Delete Guest**: Select a guest and click "Delete Guest"

### Reservations Tab
- **Add Reservation**: Select guest and room, enter dates and price, click "Add Reservation"
- **Update Reservation**: Select a reservation, change status, click "Update Reservation"
- **Delete Reservation**: Select a reservation and click "Delete Reservation"

## Database Schema

### Hotels Table
- hotel_id (INT, AUTO_INCREMENT, PRIMARY KEY)
- name (VARCHAR(100))
- location (VARCHAR(100))
- amenities (TEXT)

### Guests Table
- guest_id (INT, AUTO_INCREMENT, PRIMARY KEY)
- name (VARCHAR(100))
- email (VARCHAR(100), UNIQUE)
- phone (VARCHAR(20))
- address (TEXT)

### Rooms Table
- room_id (INT, AUTO_INCREMENT, PRIMARY KEY)
- hotel_id (INT, FOREIGN KEY)
- room_number (VARCHAR(10))
- type (ENUM: SINGLE, DOUBLE, SUITE)
- price_per_night (DECIMAL(10,2))
- is_available (BOOLEAN)

### Reservations Table
- reservation_id (INT, AUTO_INCREMENT, PRIMARY KEY)
- guest_id (INT, FOREIGN KEY)
- room_id (INT, FOREIGN KEY)
- check_in_date (DATE)
- check_out_date (DATE)
- total_price (DECIMAL(10,2))
- status (ENUM: CONFIRMED, CANCELLED, COMPLETED)

## Sample Data

The database script includes sample data for testing:
- 5 hotels in different cities
- Sample rooms for each hotel
- Sample guest profiles
- Sample reservations

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify MySQL server is running
   - Check connection parameters in DatabaseConnector.java
   - Ensure database exists and user has proper permissions

2. **ClassNotFound Exception for JDBC Driver**
   - Verify MySQL JDBC driver is in the build path
   - Check if the JAR file is correctly added to lib folder

3. **SQL Syntax Errors**
   - Ensure database schema is properly created
   - Run the database_schema.sql script completely

4. **GUI Display Issues**
   - Ensure Java Swing is properly configured
   - Check if all required imports are available

## Future Enhancements

- User authentication system
- Advanced search and filtering
- Reporting and analytics
- Email notifications for reservations
- Payment integration
- Multi-language support

## Technical Notes

- **Java Version**: Compatible with Java 8+
- **Database**: MySQL with JDBC
- **GUI Framework**: Java Swing
- **Architecture**: MVC pattern with DAO layer
- **Design Patterns**: DAO, Factory, Observer

## License

This project is for educational purposes as part of Core Java Training.

## Contact

For issues or questions, please refer to the project documentation or contact your training instructor.
