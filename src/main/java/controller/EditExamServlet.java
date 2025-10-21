package controller;

import dao.ExamDAO;
import model.ExamInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/teacher/editExam")
public class EditExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int examId = Integer.parseInt(request.getParameter("examId"));
            String examName = request.getParameter("examName");
            String subject = request.getParameter("subject");
            LocalDate examDate = LocalDate.parse(request.getParameter("examDate"));
            LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
            LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));
            int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));

            ExamInput input = new ExamInput();
            input.setExamName(examName);
            input.setSubject(subject);
            input.setExamDate(examDate);
            input.setStartTime(startTime);
            input.setEndTime(endTime);
            input.setTotalMarks(totalMarks);

            ExamDAO dao = new ExamDAO();
            boolean updated = dao.updateExam(examId, input);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/teacher/manageExams?updateSuccess=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/teacher/manageExams?updateFailed=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
