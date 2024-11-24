
# Hotel Reservation System 🏨
## Overview

The Hotel Reservation System is a robust Java-based application that enables efficient hotel room booking and guest management. This project leverages JDBC for seamless database interactions with MySQL. Designed to enhance operational efficiency, it provides essential features such as room reservation, guest details management, and dynamic database updates.

## Features

- Guest Management: Add, update, and view guest information.
- Room Booking: Book rooms based on guest preferences and availability.
- Reservation Updates: Modify existing reservations or cancel them when needed.
- Database Integration: Uses MySQL for persistent data storage.
- Dynamic Queries: Secure and efficient database operations using PreparedStatement


## Installation

1. Clone the repository:

```bash
  git clone https://github.com/ahsansiraj8948/Hotel-Reservation.git  
```
2. Import the project into your preferred IDE.

3. Set up the database:
Run the provided SQL scripts to create the database and required tables.

4. Update the database credentials in the project code:
```
String DB_URL = "jdbc:mysql://localhost:3306/your_database";  
String USER = "your_username";  
String PASSWORD = "your_password";  

```
    
### Technology Stack  

| **Component**        | **Technology Used** |  
|-----------------------|---------------------|  
| Programming Language  | Java               |  
| Database              | MySQL              |  
| Database Connectivity | JDBC               |  
| IDE                  | IntelliJ IDEA |  

## Database Queries 💻

### 1. Table Creation Queries  

#### Reservation Table  
```sql
CREATE TABLE tbl_reservation (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    GuestName VARCHAR(255) NOT NULL,
    RoomNumber INT NOT NULL,
    ContactNumber VARCHAR(15) NOT NULL,
    ReservationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
## Contributing
Contributions are welcome! If you'd like to improve the project, feel free to fork the repository and submit a pull request.

