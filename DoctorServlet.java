package hospital;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {

    private final DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String specialization = request.getParameter("specialization");
        List<Doctor> doctors;

        if (specialization == null || specialization.trim().isEmpty()) {
            doctors = doctorDao.getAllDoctors();
        } else {
            doctors = doctorDao.findDoctorsBySpecialization(specialization.trim());
        }

        out.println("<html><head><title>Doctor Records</title></head><body>");
        out.println("<h1>Doctor Records</h1>");

        // simple search form (innovation)
        out.println("<form method='get' action='doctors'>");
        out.println("Filter by specialization: <input type='text' name='specialization' value='"
                + (specialization == null ? "" : specialization) + "'/>");
        out.println("<input type='submit' value='Search'/>");
        out.println("</form><br>");

        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Specialization</th><th>Experience (years)</th></tr>");
        if (doctors != null && !doctors.isEmpty()) {
            for (Doctor doc : doctors) {
                out.println("<tr><td>" + doc.getId() + "</td><td>"
                        + doc.getName() + "</td><td>"
                        + doc.getSpecialization() + "</td><td>"
                        + doc.getExperience() + "</td></tr>");
            }
        } else {
            out.println("<tr><td colspan='4'>No doctors found.</td></tr>");
        }
        out.println("</table>");
        out.println("<br><a href='addDoctor.html'>Add New Doctor</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("docname");
        String specialization = request.getParameter("specialization");
        String expParam = request.getParameter("experience");

        String message;

        if (name == null || name.trim().isEmpty()
                || specialization == null || specialization.trim().isEmpty()
                || expParam == null || expParam.trim().isEmpty()) {

            message = "All fields are required. Please fill in all details.";

        } else {
            try {
                int exp = Integer.parseInt(expParam.trim());
                if (exp < 0) {
                    message = "Experience cannot be negative.";
                } else {
                    Doctor doctor = new Doctor(0, name.trim(), specialization.trim(), exp);
                    int result = doctorDao.saveDoctor(doctor);
                    message = (result > 0)
                            ? "Doctor added successfully!"
                            : "Error inserting doctor!";
                }
            } catch (NumberFormatException e) {
                message = "Experience must be a valid number.";
            }
        }

        out.println("<html><head><title>Add Doctor</title></head><body>");
        out.println("<h2>" + message + "</h2>");
        doGet(request, response);
        out.println("</body></html>");
    }
}

