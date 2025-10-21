package controller;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/manageQuestions")
public class AddEditDeleteQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String examIdParam = request.getParameter("examId");
        String editParam = request.getParameter("editId");
        String deleteParam = request.getParameter("deleteId");

        try {
            QuestionDAO dao = new QuestionDAO();

            if (deleteParam != null) {
                dao.deleteQuestion(Integer.parseInt(deleteParam));
                response.sendRedirect(request.getContextPath() + "/teacher/manageQuestions?examId=" + examIdParam + "&msg=Question+Deleted");
                return;
            }
            Question editQuestion = null;
            if (editParam != null) {
                editQuestion = dao.getQuestionById(Integer.parseInt(editParam));
                request.setAttribute("editQuestion", editQuestion);
            }

            List<Question> questions = dao.getQuestionsByExamId(Integer.parseInt(examIdParam));
            request.setAttribute("questions", questions);
            request.setAttribute("examId", examIdParam);
            request.getRequestDispatcher("/add_questions.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String examIdParam = request.getParameter("examId");
        String editId = request.getParameter("editId");

        try {
            QuestionDAO dao = new QuestionDAO();
            Question question = new Question();
            question.setExamId(Integer.parseInt(examIdParam));
            question.setQuestionText(request.getParameter("questionText"));
            question.setOption1(request.getParameter("option1"));
            question.setOption2(request.getParameter("option2"));
            question.setOption3(request.getParameter("option3"));
            question.setOption4(request.getParameter("option4"));
            question.setCorrectOption(Integer.parseInt(request.getParameter("correctOption")));

            if (editId == null || editId.isEmpty()) {
                dao.insertQuestion(question);
                request.setAttribute("msg", "Question Added Successfully");
            } else {
                question.setQuestionId(Integer.parseInt(editId));
                dao.updateQuestion(question);
                request.setAttribute("msg", "Question Updated Successfully");
            }

            List<Question> questions = dao.getQuestionsByExamId(Integer.parseInt(examIdParam));
            request.setAttribute("questions", questions);
            request.setAttribute("examId", examIdParam);

            request.getRequestDispatcher("/add_questions.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("examId", examIdParam);
            request.setAttribute("error", "Failed to process question");
            request.getRequestDispatcher("/add_questions.jsp").forward(request, response);
        }
    }
}
