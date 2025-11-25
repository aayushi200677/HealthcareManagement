package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "aayu@2006"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC Driver not found.");
            ex.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
