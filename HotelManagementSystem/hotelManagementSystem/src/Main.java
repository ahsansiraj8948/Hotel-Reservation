import java.sql.*;
import java.util.Scanner;

public class Main {
    private  static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private  static final String username="root";
    private  static final String password="Siraj8948@*#";


    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            while (true)
            {
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM ");
                Scanner sc=new Scanner(System.in);
                System.out.println("1. View Reservation");
                System.out.println("2. Reserve a room");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation Details");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.print("Choose an Option: ");
                int option=sc.nextInt();
                switch (option)
                {
                    case 1:
                        viewReservation(con,sc);
                        break;
                    case 2:
                        ReserveRoom(con,sc);
                        break;
                    case 3:
                        getroomNumber(con,sc);
                        break;
                    case 4:
                        updateReservation(con,sc);
                        break;
                    case 5:
                        deleteReservation(con,sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice . Try Again.");
                }
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    private static void viewReservation(Connection con,Scanner sc) throws SQLException {
        String query = "SELECT ReservationID,GuestName,ContactNumber,RoomNumber,ReservationDate FROM tbl_reservation";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultset = preparedStatement.executeQuery();
            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (resultset.next()) {
                int reservationid = resultset.getInt("ReservationID");
                String guestName = resultset.getString("GuestName");
                String contactNumber = resultset.getString("ContactNumber");
                int roomnumber = resultset.getInt("RoomNumber");
                String reservationdate = resultset.getString("ReservationDate");

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                        reservationid, guestName, roomnumber, contactNumber, reservationdate);
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void ReserveRoom(Connection con, Scanner sc) {
        System.out.print("Enter guest name: ");
        String GuestName=sc.next();
        System.out.print("Enter Room Number: ");
        int RoomNumber =sc.nextInt();
        System.out.print("Enter Mobile Number: ");
        String ContactNumber =sc.next();

        try {
            String query = "INSERT INTO tbl_reservation (GuestName, RoomNumber, ContactNumber, ReservationDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, GuestName);
            preparedStatement.setInt(2, RoomNumber);
            preparedStatement.setString(3, ContactNumber);

            int result = preparedStatement.executeUpdate();
            if(result>0)
            {
                System.out.println("REGISTRATION SUCCESSFULL.");
            }
            else {
                System.out.println("NOT REGISTERED");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void getroomNumber(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Reservation ID=");
            int ReservationID= sc.nextInt();
            System.out.print("Enter guest name=");
            String guestname= sc.next();
            String query = "SELECT RoomNumber FROM tbl_reservation WHERE ReservationID=? AND GuestName=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,ReservationID);
            preparedStatement.setString(2,guestname);
            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next())
            {
                int roomNumber = resultset.getInt("RoomNumber");
                System.out.println("Room number for Reservation ID " + ReservationID +
                        " and Guest " + guestname + " is: " + roomNumber);
            } else {
                System.out.println("Reservation not found for the given ID and guest name.");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void updateReservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Reservation ID=");
            int ReservationID = sc.nextInt();

            if (!reservationExists(con,ReservationID))
            {
                System.out.print("Reservation not found for the given ID.");
            }
            else {
                System.out.print("Enter new guest name: ");
                String newGuestName = sc.next();
                System.out.print("Enter new room number: ");
                int newRoomNumber = sc.nextInt();
                System.out.print("Enter new contact number: ");
                String newContactNumber = sc.next();

                String query = "UPDATE tbl_reservation SET GuestName = ?, RoomNumber = ?, ContactNumber = ? WHERE ReservationID = ?";
                PreparedStatement preparedStatement =con.prepareStatement(query);
                preparedStatement.setString(1,newGuestName);
                preparedStatement.setInt(2,newRoomNumber);
                preparedStatement.setString(3,newContactNumber);
                preparedStatement.setInt(4,ReservationID);
                int result= preparedStatement.executeUpdate();
                if(result>0)
                {
                    System.out.println("Reservation updated successfully!");
                }
                else {
                    System.out.println("Reservation update failed.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void deleteReservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter reservation ID to delete: ");
            int reservationId = sc.nextInt();

            if (!reservationExists(con, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            String sql = "DELETE FROM tbl_reservation WHERE ReservationID = " + reservationId;

            try (Statement statement = con.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation deleted successfully!");
                } else {
                    System.out.println("Reservation deletion failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean reservationExists(Connection con, int reservationID) throws SQLException {
        String query=  "SELECT ReservationID FROM tbl_reservation WHERE ReservationID = ?";

        try {
            PreparedStatement preparedStatement= con.prepareStatement(query);
            preparedStatement.setInt(1,reservationID);
            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle database errors as needed
        }
    }
    private static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
    }
}