<%@ page import="java.util.List" %>
<%@ page import="model.Examcard" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exams</title>
    <style>
        h2 { margin-bottom: 28px; }
        table { width: 80%; margin: 0 auto; border-collapse: collapse; background: #f0f3ff; }
        th, td { padding: 16px 12px; border-bottom: 1px solid #dcdcdc; }
        th { background: #667eea; color: #fff; font-size: 17px; }
        tr:hover { background: #ebeef9; }
    </style>
</head>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>




<body>
<h2>Available Exams</h2>
<div>Exam List Size: ${fn:length(examList)}</div>
<c:choose>
    <c:when test="${empty examList}">
        <h2>No Exams Uploaded Yet</h2>
    </c:when>
    <c:otherwise>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Subject</th>
                    <th>Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Total Marks</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="exam" items="${examList}">
                    <tr>
                        <td><c:out value="${exam.examName}" /></td>
                        <td><c:out value="${exam.subject}" /></td>
                        <td><c:out value="${exam.examDate}" /></td>
                        <td><c:out value="${exam.startTime}" /></td>
                        <td><c:out value="${exam.endTime}" /></td>
                        <td><c:out value="${exam.totalMarks}" /></td>
                        <td>
                          <a href="${pageContext.request.contextPath}/teacher/viewExam?examId=${exam.examId}">Take Exam</a>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
