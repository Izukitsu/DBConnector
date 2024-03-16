import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection1 {

    // Static connection object shared  across all instances of the class
    private static Connection con;

    // Method to establish a database connection
    public void getDBConn() {
        // Synchronizing on the class object to ensure thread safety
        synchronized (DBConnection.class) {
            try {
                // Checking if the connection is null or closed
                if (getCon() == null || getCon().isClosed()) {
                    try {
                        // Database URL (replace with your database URL)
                        String url = "jdbc:mysql://localhost/student_list";
                        // Loading the MySQL JDBC driver
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // Creating a new database connection
                        setCon(DriverManager.getConnection(url, "root", "1234"));
                    } catch (Exception e) {
                        // Handling any exceptions that occur during connection setup
                        e.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                // Handling SQL exceptions
                ex.printStackTrace();
            }
        }
    }

    // Getter method for the connection object
    public static Connection getCon() {
        return con;
    }

    // Setter method for the connection object
    public static void setCon(Connection aCon) {
        con = aCon;
    }

    // Method to close the database connection
    public static void closeConnection() {
        try {
            // Checking if the connection is not null and is not already closed
            if (con != null && !con.isClosed()) {
                // Closing the connection
                con.close();
            }
        } catch (Exception e) {
            // Handling any exceptions that occur during connection closing
            e.printStackTrace();
        }
    }
}