package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.TeacherDAO;
import model.Teacher;

@WebServlet("/TeacherLoginServlet")
public class TeacherLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // get email and password from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Debug prints to check values
        System.out.println("Email received: " + email);
        System.out.println("Password received: " + password);

        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.login(email, password);

        if (teacher != null) {
            HttpSession session = request.getSession();
            session.setAttribute("teacher", teacher);

            System.out.println("Redirecting to: " + request.getContextPath() + "/teacher-dashboard.jsp");
            response.sendRedirect(request.getContextPath() + "/teacher-dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid credentials!");
            RequestDispatcher rd = request.getRequestDispatcher("/teacher-login.html");
            rd.forward(request, response);
        }
    }
}
