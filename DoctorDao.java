package hospital;

import java.util.List;

public interface DoctorDao {
    int saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();

    boolean existsByName(String name);
    List<Doctor> searchBySpecialization(String specialization);

    int updateDoctorExperience(String name, int experience);
    int deleteDoctor(String name);
}

