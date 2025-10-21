<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add/Edit Question</title>
 
</head>
<body>
<h2>Manage Questions for Exam ID: <c:out value="${examId}" /></h2>

<c:if test="${msg != null}">
    <div class="alert alert-success">${msg}</div>
</c:if>
<c:if test="${error != null}">
    <div class="alert alert-error">${error}</div>
</c:if>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Question</th>
            <th>Option 1</th>
            <th>Option 2</th>
            <th>Option 3</th>
            <th>Option 4</th>
            <th>Correct</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="q" items="${questions}">
            <tr>
                <td>${q.questionId}</td>
                <td>${q.questionText}</td>
                <td>${q.option1}</td>
                <td>${q.option2}</td>
                <td>${q.option3}</td>
                <td>${q.option4}</td>
                <td>${q.correctOption}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/teacher/manageQuestions?examId=${examId}&editId=${q.questionId}">Edit</a>
                    |
                    <a href="${pageContext.request.contextPath}/teacher/manageQuestions?examId=${examId}&deleteId=${q.questionId}" onclick="return confirm('Delete this question?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<form action="${pageContext.request.contextPath}/teacher/manageQuestions" method="post">
    <input type="hidden" name="examId" value="${examId}" />
    <input type="hidden" name="editId" value="${editQuestion.questionId != null ? editQuestion.questionId : ''}" />

    <label>Question Text:</label>
    <input type="text" name="questionText" required value="${editQuestion.questionText != null ? editQuestion.questionText : ''}" /><br/>
    <label>Option 1:</label>
    <input type="text" name="option1" required value="${editQuestion.option1 != null ? editQuestion.option1 : ''}" /><br/>
    <label>Option 2:</label>
    <input type="text" name="option2" required value="${editQuestion.option2 != null ? editQuestion.option2 : ''}" /><br/>
    <label>Option 3:</label>
    <input type="text" name="option3" required value="${editQuestion.option3 != null ? editQuestion.option3 : ''}" /><br/>
    <label>Option 4:</label>
    <input type="text" name="option4" required value="${editQuestion.option4 != null ? editQuestion.option4 : ''}" /><br/>
    <label>Correct Option (1-4):</label>
    <input type="number" name="correctOption" min="1" max="4" required value="${editQuestion.correctOption != 0 ? editQuestion.correctOption : ''}" /><br/>

    <button type="submit">${editQuestion != null ? 'Update' : 'Add'} Question</button>
    <c:if test="${editQuestion != null}">
        <a href="${pageContext.request.contextPath}/teacher/manageQuestions?examId=${examId}">Cancel Edit</a>
    </c:if>
</form>

<a href="${pageContext.request.contextPath}/teacher/manageExams" class="back-btn">← Back to Exams</a>
</body>
</html>
