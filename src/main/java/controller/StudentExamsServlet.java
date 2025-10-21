package controller;

import dao.QuestionDAO;
import model.Question;
import model.Student;
import dao.ExamDAO;
import model.Examcard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student/exams")
public class StudentExamsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExamDAO dao = new ExamDAO();
            List<Examcard> publishedExams = dao.getPublishedExams();
            request.setAttribute("examList", publishedExams);
            request.getRequestDispatcher("/exams.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to fetch published exams.");
            request.getRequestDispatcher("/exams.jsp").forward(request, response);
        }
    }
}
