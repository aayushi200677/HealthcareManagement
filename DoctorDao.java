package hospital;

import java.util.List;

public interface DoctorDao {
    int saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    int updateDoctorExperience(String name, int experience);
    int deleteDoctor(String name);
}
