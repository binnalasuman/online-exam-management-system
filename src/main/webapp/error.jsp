<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<style>
body { font-family: Arial, sans-serif; padding: 40px; background-color: #f8d7da; color: #721c24; }
h2 { font-weight: 700; }
pre { background-color: #f5c6cb; padding: 15px; border-radius: 6px; overflow-x: auto; }
</style>
</head>
<body>
    <h2>An error occurred</h2>
    <p>${errorMsg}</p>
    <pre><%= exception %></pre>
    <a href="manageExams.jsp">Back to Manage Exams</a>
</body>
</html>
