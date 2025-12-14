package hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM doctors WHERE name = ? LIMIT 1";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error in existsByName()", e);
        }
    }

    @Override
    public int saveDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors (name, specialization, experience) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setInt(3, doctor.getExperience());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB error in saveDoctor()", e);
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        String sql = "SELECT id, name, specialization, experience FROM doctors ORDER BY id DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                doctorList.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error in getAllDoctors()", e);
        }
        return doctorList;
    }

    @Override
    public List<Doctor> searchBySpecialization(String specialization) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT id, name, specialization, experience FROM doctors WHERE specialization LIKE ? ORDER BY name";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + specialization + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Doctor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error in searchBySpecialization()", e);
        }
        return list;
    }

    @Override
    public int updateDoctorExperience(String name, int experience) {
        String sql = "UPDATE doctors SET experience = ? WHERE name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, experience);
            ps.setString(2, name);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB error in updateDoctorExperience()", e);
        }
    }

    @Override
    public int deleteDoctor(String name) {
        String sql = "DELETE FROM doctors WHERE name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB error in deleteDoctor()", e);
        }
    }
}

