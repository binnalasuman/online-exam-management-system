<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- assume controller populates request attribute "examList" -->
<h2>Manage Exams</h2>

<!-- Upload form -->
<form action="UploadExamServlet" method="post" enctype="multipart/form-data">
  <input name="examName" required placeholder="Exam name" />
  <input name="subject" required placeholder="Subject" />
  <input name="examDate" type="date" required />
  <input name="startTime" type="time" required />
  <input name="endTime" type="time" required />
  <input name="totalMarks" type="number" required />
  <input name="examFile" type="file" />
  <button type="submit">Upload Exam</button>
</form>

<!-- Existing exams -->
<table>
  <thead><tr><th>Name</th><th>Date</th><th>Status</th><th>Action</th></tr></thead>
  <tbody>
   <c:forEach var="e" items="${examList}">
     <tr>
       <td>${e.exam_name}</td>
       <td>${e.exam_date}</td>
       <td>${e.status}</td>
       <td>
         <form action="DeleteExamServlet" method="post" style="display:inline">
           <input type="hidden" name="examId" value="${e.exam_id}" />
           <button type="submit" onclick="return confirm('Cancel this exam?')">Cancel</button>
         </form>
       </td>
     </tr>
   </c:forEach>
  </tbody>
</table>
