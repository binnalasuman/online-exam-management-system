package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import model.Examcard;

@WebServlet("/student/dashboard")
public class StudentDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExamDAO dao = new ExamDAO();
            List<Examcard> publishedExams = dao.getPublishedExams();
            request.setAttribute("examList", publishedExams);
            request.getRequestDispatcher("/student-dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to load dashboard.");
            request.getRequestDispatcher("/student-dashboard.jsp").forward(request, response);
        }
    }
}
