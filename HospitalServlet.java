package hospital;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HospitalServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String cityFilter = request.getParameter("city");
        List<User> users;

        if (cityFilter != null && !cityFilter.trim().isEmpty()) {
            users = userDao.findUsersByCity(cityFilter.trim());
        } else {
            users = userDao.getAllUsers();
        }

        out.println("<html><head><title>User Records</title></head><body>");
        out.println("<h1>Current User Records</h1>");

        // Search/filter form – innovation
        out.println("<form method='get' action='HospitalServlet'>");
        out.println("Filter by city: <input type='text' name='city'/>");
        out.println("<input type='submit' value='Search'/>");
        out.println("</form><br>");

        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>City</th></tr>");

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getId() + "</td>");
                out.println("<td>" + user.getName() + "</td>");
                out.println("<td>" + user.getAge() + "</td>");
                out.println("<td>" + user.getCity() + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='4'>No users found.</td></tr>");
        }

        out.println("</table>");
        out.println("<br><a href='addRecord.html'>Add New Record</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String ageParam = request.getParameter("age");
        String city = request.getParameter("city");

        String message;

        if (name == null || name.trim().isEmpty()
                || ageParam == null || ageParam.trim().isEmpty()
                || city == null || city.trim().isEmpty()) {

            message = "All fields are required. Please fill in all details.";

        } else {
            try {
                int age = Integer.parseInt(ageParam.trim());
                User user = new User(0, name.trim(), age, city.trim());

                if (!user.isValid()) {
                    message = "Invalid user data. Age must be between 1 and 120 and fields cannot be blank.";
                } else {
                    int result = userDao.saveUser(user);
                    message = (result > 0)
                            ? "User added successfully!"
                            : "An error occurred while adding the user. Please try again.";
                }
            } catch (NumberFormatException e) {
                message = "Age must be a valid number.";
            }
        }

        out.println("<html><head><title>Add User</title></head><body>");
        out.println("<h2>" + message + "</h2>");
        doGet(request, response);
        out.println("</body></html>");
    }
}


