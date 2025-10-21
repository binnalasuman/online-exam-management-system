<%@ page import="model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Examcard" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
        response.sendRedirect("student-login.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Student Dashboard - Modern Style</title>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap');
    * { box-sizing: border-box; }
    body { margin: 0; font-family: 'Poppins', sans-serif; background: linear-gradient(135deg, #ffecd2 0%, #fcb69f 100%); color: #3e3e3e; display: flex; min-height: 100vh; overflow-x: hidden; }
    .sidebar { width: 260px; background: #fff9f6cc; backdrop-filter: saturate(180%) blur(20px); box-shadow: 0 8px 24px rgba(252, 182, 159, 0.25); display: flex; flex-direction: column; padding: 2rem 1.5rem; border-radius: 0 24px 24px 0; }
    .sidebar h2 { font-weight: 700; color: #cf4b26; text-align: center; letter-spacing: 2px; margin-bottom: 2.5rem; }
    .sidebar nav a { display: flex; align-items: center; gap: 14px; padding: 14px 18px; margin-bottom: 1rem; font-weight: 600; font-size: 1.05rem; color: #cf4b2677; border-radius: 12px; transition: all 0.3s ease; text-decoration: none; }
    .sidebar nav a:hover, .sidebar nav a.active { background: #fcb69f; color: #ffffffcc; box-shadow: 0 4px 14px #fcb69fcc; transform: translateX(4px); }
    .sidebar nav a svg { flex-shrink: 0; width: 20px; height: 20px; fill: currentColor; }
    .main-content { flex-grow: 1; padding: 3rem 4rem; overflow-y: auto; }
    header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 3rem; }
    header h1 { font-weight: 700; font-size: 2.5rem; color: #cf4b26; text-shadow: 1px 2px 6px rgba(252, 182, 159, 0.5); }
    .profile { display: flex; align-items: center; gap: 1rem; background: rgba(255 255 255 / 0.9); padding: 10px 16px; border-radius: 12px; box-shadow: 0 6px 14px rgba(252, 182, 159, 0.25); }
    .profile img { width: 52px; height: 52px; border-radius: 50%; object-fit: cover; border: 2px solid #fcb69f; }
    .profile span { font-weight: 700; font-size: 1.15rem; color: #cf4b26; }
    .cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 2rem; margin-bottom: 3rem; }
    .card { background: white; border-radius: 20px; padding: 2rem 2.5rem; box-shadow: 0 8px 24px rgba(252, 182, 159, 0.25); display: flex; flex-direction: column; align-items: center; transition: transform 0.3s ease, box-shadow 0.3s ease; cursor: default; }
    .card:hover { transform: translateY(-8px); box-shadow: 0 12px 40px rgba(252, 182, 159, 0.45); }
    .card h3 { font-weight: 600; color: #cf4b26; margin-bottom: 1rem; font-size: 1.3rem; text-align: center; }
    .card p { font-size: 2.8rem; font-weight: 700; color: #fcb69f; margin: 0; letter-spacing: 2px; }
    table { width: 100%; border-collapse: separate; border-spacing: 0 14px; }
    th, td { padding: 16px 20px; text-align: left; color: #cf4b26; }
    thead th { font-weight: 700; font-size: 1rem; color: #b53a12; }
    tbody tr { background: #fff9f6cc; border-radius: 16px; box-shadow: 0 8px 24px rgba(252, 182, 159, 0.15); transition: box-shadow 0.3s ease; cursor: default; }
    tbody tr:hover { box-shadow: 0 12px 40px rgba(252, 182, 159, 0.3); }
    tbody td { font-weight: 600; font-size: 1rem; }
    tbody td.status { font-weight: 700; text-transform: uppercase; letter-spacing: 1px; }
    .status.Completed { color: #27ae60; }
    .status.Pending { color: #e67e22; }
    .status.Missed { color: #c0392b; }
    @media (max-width: 768px) { .main-content { padding: 2rem 2rem; } .sidebar { width: 100%; flex-direction: row; padding: 1rem 0.5rem; border-radius: 0; overflow-x: auto; } .sidebar h2 { display: none; } .sidebar nav a { flex: 1; margin: 0 0.25rem; padding: 0.5rem 0; justify-content: center; font-size: 0.9rem; } header { flex-direction: column; gap: 1.5rem; } }
  </style>
</head>
<body>
  <aside class="sidebar">
    <h2>Student Panel</h2>
    <nav>
      <a href="#" class="active" title="Dashboard">
        <svg viewBox="0 0 24 24"><path d="M3 9.5l9-7 9 7V21a1 1 0 0 1-1 1h-5v-6H9v6H4a1 1 0 0 1-1-1z"/></svg>
        Dashboard
      </a>
      <a href="#" title="Profile">
        <svg viewBox="0 0 24 24"><rcle cx="12" cy="8" r="4"4"/><path d="M4 20c0-4 8-4 8-4s8 0 8 4v1H4v-1z"/></svg>
        Profile
      </a>
      <a href="student/exams" title="Exams">
        <svg viewBox="0 0 24 24"><path d="M19 4h-14a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-12a2 2 0 0 0-2-2zM7 20v-6h10v6H7z"/></svg>
         Exams
      </a>
      <a href="#" title="Results">
        <svg viewBox="0 0 24 24"><path d="M3 17h18v2H3zM6 10h3v7H6zM11 6h3v11h-3zM16 13h3v4h-3z"/></svg>
        Results
      </a>
      <a href="#" title="Messages">
        <svg viewBox="0 0 24 24"><path d="M21 6a2 2 0 0 0-2-2H5a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h14l4 4V6z"/></svg>
        Messages
      </a>
      <a href="#" title="Logout">
        <svg viewBox="0 0 24 24"><path d="M16 17l5-5-5-5M21 12H9M13 5v-2H4v14h9v-2"/></svg>
        Logout
      </a>
    </nav>
  </aside>

  <main class="main-content">
    <header>
      <h1>Welcome, <%= student.getName() %>!</h1>
      <div class="profile">
      <svg xmlns="http://www.w3.org/2000/svg" width="52" height="52" viewBox="0 0 24 24" fill="#cf4b26" style="border-radius: 50%; background: #fff9f6cc; padding: 8px; border: 2px solid #fcb69f;">
        <path d="M12 12c2.7 0 5-2.3 5-5s-2.3-5-5-5-5 2.3-5 5 2.3 5 5 5zm0 2c-3.3 0-10 1.7-10 5v3h20v-3c0-3.3-6.7-5-10-5z"/>
      </svg>
         <span><%= student.getName() %></span> 
      </div>
    </header>

    <section class="cards">
      <div class="card"><h3>Upcoming Exams</h3><p>3</p></div>
      <div class="card"><h3>Completed Exams</h3><p>5</p></div>
      <div class="card"><h3>Pending Results</h3><p>2</p></div>
      <div class="card"><h3>Messages</h3><p>1</p></div>
      <div class="card"><h3>Performance</h3><p>1</p></div>
    </section>

<section>
<h2 style="color:#cf4b26; margin-bottom:1.5rem;">Available Exams</h2>
<c:choose>
    <c:when test="${empty examList}">
        <h2>No Exams Available</h2>
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
                            <a href="viewExam.jsp">Take Exam</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</section>

  

  </main>
</body>
</html>
