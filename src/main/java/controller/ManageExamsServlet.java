package controller;

import dao.ExamDAO;
import model.Examcard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/manageExams")
public class ManageExamsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ExamDAO examDAO = new ExamDAO();
            List<Examcard> examList = examDAO.getAllExams();
            request.setAttribute("examList", examList);
            request.getRequestDispatcher("/manage_exam.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
