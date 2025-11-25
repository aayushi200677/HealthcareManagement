package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoctorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        DoctorDao doctorDao = new DoctorDaoImpl();
        List<Doctor> doctors = doctorDao.getAllDoctors();

        out.println("<h1>Current Doctor Records</h1>");
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Specialization</th><th>Experience</th></tr>");
        for (Doctor doc : doctors) {
            out.println("<tr><td>" + doc.getId() + "</td><td>"
                        + doc.getName() + "</td><td>"
                        + doc.getSpecialization() + "</td><td>"
                        + doc.getExperience() + "</td></tr>");
        }
        out.println("</table>");
        out.println("<br><a href='addRecord.html'>Add New Doctor</a>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("docname");
        String specialization = request.getParameter("specialization");
        String expParam = request.getParameter("experience");

        String message = "";

        if (name != null && specialization != null && expParam != null) {
            int experience = Integer.parseInt(expParam);
            DoctorDao doctorDao = new DoctorDaoImpl();
            Doctor doc = new Doctor(0, name, specialization, experience);
            int result = doctorDao.saveDoctor(doc);

            message = (result > 0) ? "Doctor added successfully!" : "Error inserting doctor!";
        } else {
            message = "Invalid input!";
        }

        out.println("<h1>" + message + "</h1>");
        doGet(request, response);
    }
}
