package controller;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/addQuestion")
public class AddQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String examIdParam = request.getParameter("examId");
        if (examIdParam == null || examIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/teacher/manageExams");
            return;
        }

        int examId = Integer.parseInt(examIdParam);
        try {
            QuestionDAO questionDAO = new QuestionDAO();
            List<Question> questions = questionDAO.getQuestionsByExamId(examId);
            request.setAttribute("questions", questions);
            request.setAttribute("examId", examId);
            request.getRequestDispatcher("/add_questions.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int examId = Integer.parseInt(request.getParameter("examId"));
        String questionText = request.getParameter("questionText");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        int correctOption = Integer.parseInt(request.getParameter("correctOption"));

        Question question = new Question();
        question.setExamId(examId);
        question.setQuestionText(questionText);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectOption(correctOption);

        try {
            QuestionDAO dao = new QuestionDAO();
            dao.insertQuestion(question);

            List<Question> questions = dao.getQuestionsByExamId(examId);
            request.setAttribute("questions", questions);
            request.setAttribute("examId", examId);
            request.setAttribute("msg", "Question Added Successfully");

            request.getRequestDispatcher("/add_questions.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("examId", examId);
            request.setAttribute("error", "Failed to add question");
            request.getRequestDispatcher("/add_questions.jsp").forward(request, response);
        }
    }
}
