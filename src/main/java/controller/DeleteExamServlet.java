package controller;

import dao.ExamDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/teacher/deleteExam")
public class DeleteExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int examId = Integer.parseInt(request.getParameter("examId"));
            boolean success = new ExamDAO().deleteExam(examId);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/teacher/manageExams?delok=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/teacher/manageExams?delerr=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/teacher/manageExams?delerr=1");
        }
    }
}
