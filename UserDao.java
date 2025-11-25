package hospital;

import java.util.List;

public interface UserDao {
    int saveUser(User user);
    List<User> getAllUsers();
    int updateUserCity(String name, String city);
    int deleteUser(String name);
}

