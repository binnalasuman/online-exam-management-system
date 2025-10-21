package controller;

import dao.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/teacher/deleteQuestion")
public class DeleteQuestionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        int examId = Integer.parseInt(request.getParameter("examId"));
        try {
            new QuestionDAO().deleteQuestion(questionId);
            response.sendRedirect(request.getContextPath() + "/teacher/manageQuestions?examId=" + examId + "&msg=Question+Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/teacher/manageQuestions?examId=" + examId + "&error=Delete+Failed");
        }
    }
}
