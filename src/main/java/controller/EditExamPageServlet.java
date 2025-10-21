package controller;

import dao.ExamDAO;
import model.Examcard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/teacher/editExamPage")
public class EditExamPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examIdStr = request.getParameter("examId");
        if (examIdStr == null) {
            response.sendRedirect(request.getContextPath() + "/teacher/manageExams");
            return;
        }

        int examId = Integer.parseInt(examIdStr);
        ExamDAO dao = new ExamDAO();
        try {
            Examcard exam = dao.getExamById(examId);
            if (exam == null) {
                response.sendRedirect(request.getContextPath() + "/teacher/manageExams");
                return;
            }
            request.setAttribute("exam", exam);
            request.getRequestDispatcher("/WEB-INF/EditExam.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
