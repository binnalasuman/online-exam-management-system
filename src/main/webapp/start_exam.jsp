<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Start Exam</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef2f3;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #2c3e50;
            text-align: center;
        }
        form {
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
            max-width: 800px;
            margin: auto;
        }
        .question {
            margin-bottom: 20px;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        button {
            background: #27ae60;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }
        button:hover {
            background: #219150;
        }
    </style>
</head>
<body>

<h2>Online Exam</h2>

<form action="submitExam" method="post">
    <input type="hidden" name="examId" value="${examId}" />

    <c:forEach var="q" items="${questions}" varStatus="loop">
        <div class="question">
            <p><b>Q${loop.index + 1}: ${q.questionText}</b></p>
            <label><input type="radio" name="q${q.questionId}" value="1"> ${q.option1}</label><br>
            <label><input type="radio" name="q${q.questionId}" value="2"> ${q.option2}</label><br>
            <label><input type="radio" name="q${q.questionId}" value="3"> ${q.option3}</label><br>
            <label><input type="radio" name="q${q.questionId}" value="4"> ${q.option4}</label><br>
        </div>
    </c:forEach>

    <center><button type="submit">Submit Exam</button></center>
</form>

</body>
</html>
