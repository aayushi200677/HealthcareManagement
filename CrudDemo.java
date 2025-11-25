package hospital;

import java.util.List;

public class CrudDemo {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        // 1. INSERT (Create)
        User newUser = new User(0, "Amit Kumar", 24, "Delhi");
        int inserted = userDao.saveUser(newUser);
        System.out.println("Record inserted: " + inserted);

        // 2. SELECT (Read) - Now using User class
        List<User> users = userDao.getAllUsers();
        System.out.println("\nCurrent users table (using User class):");
        for (User user : users) {
            System.out.println(user);  // outputs using User.toString()
        }

        // 3. UPDATE
        int rowsUpdated = userDao.updateUserCity("Amit Kumar", "Mumbai");
        System.out.println("\nRows updated: " + rowsUpdated);

        // 4. DELETE
        int rowsDeleted = userDao.deleteUser("Amit Kumar");
        System.out.println("\nRows deleted: " + rowsDeleted);
    }
}
