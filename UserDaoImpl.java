package hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String INSERT_SQL =
            "INSERT INTO users (name, age, city) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL =
            "SELECT id, name, age, city FROM users";
    private static final String UPDATE_CITY_SQL =
            "UPDATE users SET city = ? WHERE name = ?";
    private static final String DELETE_SQL =
            "DELETE FROM users WHERE name = ?";
    private static final String FIND_BY_CITY_SQL =
            "SELECT id, name, age, city FROM users WHERE city = ?";

    @Override
    public int saveUser(User user) {
        if (user == null || !user.isValid()) {
            return 0;
        }
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getCity());
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while saving user: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("city")
                );
                list.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error while fetching users: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateUserCity(String name, String city) {
        if (name == null || name.trim().isEmpty()
                || city == null || city.trim().isEmpty()) {
            return 0;
        }
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CITY_SQL)) {

            ps.setString(1, city.trim());
            ps.setString(2, name.trim());
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while updating user city: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            return 0;
        }
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

            ps.setString(1, name.trim());
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while deleting user: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> findUsersByCity(String city) {
        List<User> list = new ArrayList<>();
        if (city == null || city.trim().isEmpty()) {
            return list;
        }

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_CITY_SQL)) {

            ps.setString(1, city.trim());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("city")
                    );
                    list.add(user);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error while searching users by city: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}

