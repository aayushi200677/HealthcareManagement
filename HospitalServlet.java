package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HospitalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getAllUsers();

        out.println("<h1>Current User Records</h1>");
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Age</th><th>City</th></tr>");
        for (User user : users) {
            out.println("<tr><td>" + user.getId() + "</td><td>" +
                        user.getName() + "</td><td>" +
                        user.getAge() + "</td><td>" +
                        user.getCity() + "</td></tr>");
        }
        out.println("</table>");
        out.println("<br><a href='addRecord.html'>Add New Record</a>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String ageParam = request.getParameter("age");
        String city = request.getParameter("city");

        String message = "";

        if (name != null && ageParam != null && city != null) {
            int age = Integer.parseInt(ageParam);
            UserDao userDao = new UserDaoImpl();
            User user = new User(0, name, age, city);
            int result = userDao.saveUser(user);

            if (result > 0) {
                message = "User added successfully!";
            } else {
                message = "Error inserting user!";
            }
        } else {
            message = "Invalid input!";
        }

        out.println("<h1>" + message + "</h1>");
        // Show current records
        doGet(request, response);
    }
}
