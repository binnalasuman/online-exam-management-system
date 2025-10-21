package controller;

import dao.ExamDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/teacher/publishExam")
public class PublishExamServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("PublishExamServlet starting");
        System.out.println("Received examId: " + request.getParameter("examId"));

        String examIdParam = request.getParameter("examId");
        try {
            int examId = Integer.parseInt(examIdParam);
            ExamDAO examDao = new ExamDAO();
            examDao.setExamPublished(examId);
            response.sendRedirect(request.getContextPath() + "/teacher/manageExams?msg=Exam+Published+Successfully");
        } catch (Exception e) {
            e.printStackTrace(); 
            response.sendRedirect(request.getContextPath() + "/teacher/manageExams?error=" 
                + java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }
}

