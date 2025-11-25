package hospital;

import java.sql.*;
import java.util.*;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public int saveDoctor(Doctor doctor) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO doctors (name, specialization, experience) VALUES (?, ?, ?)")) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setInt(3, doctor.getExperience());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM doctors")) {
            while (rs.next()) {
                doctorList.add(new Doctor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public int updateDoctorExperience(String name, int experience) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "UPDATE doctors SET experience = ? WHERE name = ?")) {
            ps.setInt(1, experience);
            ps.setString(2, name);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteDoctor(String name) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM doctors WHERE name = ?")) {
            ps.setString(1, name);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
