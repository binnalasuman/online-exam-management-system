package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.StudentDAO;
import model.Student;

@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        StudentDAO dao = new StudentDAO();
        Student student = dao.login(email, password);

        if (student != null) {
            HttpSession session = request.getSession();
            session.setAttribute("student", student);

            // Debug: print the redirect URL
            System.out.println("Redirecting to: " + request.getContextPath() + "/student-dashboard.jsp");

            response.sendRedirect(request.getContextPath() + "/student-dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid credentials!");
            RequestDispatcher rd = request.getRequestDispatcher("/student-login.html");
            rd.forward(request, response);
            

        }
    }
}
