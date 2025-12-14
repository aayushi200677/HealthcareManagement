package hospital;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            if (conn != null) {
                System.out.println("Database Connected Successfully!");
            } else {
                System.out.println("Connection returned null.");
            }
        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}
