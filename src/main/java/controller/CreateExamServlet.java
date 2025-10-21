package controller;

import dao.ExamDAO;
import model.Examcard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/createExam")
public class CreateExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String examId = request.getParameter("examId");
        request.setAttribute("examId", examId);

        List<Examcard> examList = null;
        try {
            examList = new ExamDAO().getAllExams();  
        } catch (Exception e) {
            e.printStackTrace();
            examList = List.of();  
        }

        request.setAttribute("examList", examList);
        request.getRequestDispatcher("/WEB-INF/createExam.jsp").forward(request, response);
    }
}
