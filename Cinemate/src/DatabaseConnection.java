import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/cinemate"; // Replace 'cinemate' with your DB name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL Database!");
        } catch (ClassNotFoundException e) {
            System.out.println(" MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args) {
        getConnection(); // Test connection
    }
}
