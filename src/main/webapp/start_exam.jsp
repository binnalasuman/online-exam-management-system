<%@ page import="java.util.List" %>
<%@ page import="model.Examcard" %>
<%@ page import="model.Question" %>
<%
    Examcard exam = (Examcard) request.getAttribute("exam");
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    int duration = exam.getDurationMinutes();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Start Exam</title>
    <script>
        let duration = <%= duration %> * 60; // in seconds
        let timerDisplay = null;

        function startTimer() {
            timerDisplay = document.getElementById('timer');
            let time = duration;
            let interval = setInterval(function () {
                let min = Math.floor(time / 60);
                let sec = time % 60;
                timerDisplay.textContent = min + "m " + (sec < 10 ? "0" + sec : sec) + "s";
                if (time <= 0) {
                    clearInterval(interval);
                    document.getElementById('examForm').submit();
                }
                time--;
            }, 1000);
        }
        window.onload = startTimer;
    </script>
</head>
<body>
    <h2><%= exam.getExamName() %></h2>
    <div>Time Remaining: <span id="timer"></span></div>

    <form id="examForm" action="<%= request.getContextPath() + "/student/submitExam" %>" method="post">
        <input type="hidden" name="examId" value="<%= exam.getExamId() %>" />
        <%
            for (Question q : questions) {
        %>
        <div>
            <b>Q<%= q.getQuestionId() %>: <%= q.getQuestionText() %></b><br/>
            <label><input type="radio" name="q<%= q.getQuestionId() %>" value="1" /> <%= q.getOption1() %></label><br/>
            <label><input type="radio" name="q<%= q.getQuestionId() %>" value="2" /> <%= q.getOption2() %></label><br/>
            <label><input type="radio" name="q<%= q.getQuestionId() %>" value="3" /> <%= q.getOption3() %></label><br/>
            <label><input type="radio" name="q<%= q.getQuestionId() %>" value="4" /> <%= q.getOption4() %></label><br/><br/>
        </div>
        <%
            }
        %>
        <button type="submit">Submit Exam</button>
    </form>
</body>
</html>
