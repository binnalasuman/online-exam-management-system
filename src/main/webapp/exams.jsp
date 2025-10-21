<!-- exam.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        .menu { margin: 20px 0; }
        .exam { cursor: pointer; padding: 10px; background: #f0f0f0; margin: 5px 0; border-radius: 5px; }
        .exam:hover { background: #dcdcdc; }
        .sub-options { margin-left: 20px; display: none; }
        .sub-options div { padding: 5px; cursor: pointer; }
        .sub-options div:hover { background: #e0e0ff; }
        .questions { margin-top: 20px; padding: 15px; border: 1px solid #ccc; border-radius: 8px; background: #fafafa; display: none; }
        .question { margin-bottom: 15px; }
    </style>
    <script>
        function toggleOptions(examId) {
            // Hide all
            document.querySelectorAll(".sub-options").forEach(div => div.style.display = "none");
            document.querySelectorAll(".questions").forEach(div => div.style.display = "none");

            // Show only selected exam’s options
            let selected = document.getElementById(examId);
            if (selected) {
                selected.style.display = "block";
            }
        }

        function showQuestions(section) {
            // Hide all questions first
            document.querySelectorAll(".questions").forEach(div => div.style.display = "none");

            // Show selected section questions
            let q = document.getElementById(section + "-questions");
            if (q) {
                q.style.display = "block";
            }
        }
    </script>
</head>
<body>
    <h2>Welcome Student</h2>
    <h3>Select an Exam:</h3>

    <div class="menu">
        <!-- Java Test -->
        <div class="exam" onclick="toggleOptions('java-options')">Java Test</div>
        <div id="java-options" class="sub-options">
            <div onclick="showQuestions('java-basic')">Java Basic</div>
            <div onclick="showQuestions('java-core')">Java Core</div>
            <div onclick="showQuestions('java-advanced')">Java Advanced</div>
            <div onclick="showQuestions('java-logic')">Java Code Logics</div>
        </div>

        <!-- Angular Test -->
        <div class="exam" onclick="toggleOptions('angular-options')">Angular Test</div>
        <div id="angular-options" class="sub-options">
            <div onclick="showQuestions('angular-basic')">Angular Basics</div>
            <div onclick="showQuestions('angular-components')">Angular Components</div>
            <div onclick="showQuestions('angular-directives')">Angular Directives</div>
            <div onclick="showQuestions('angular-services')">Angular Services</div>
        </div>

        <!-- Spring Test -->
        <div class="exam" onclick="toggleOptions('spring-options')">Spring Test</div>
        <div id="spring-options" class="sub-options">
            <div onclick="showQuestions('spring-core')">Spring Core</div>
            <div onclick="showQuestions('spring-mvc')">Spring MVC</div>
            <div onclick="showQuestions('spring-boot')">Spring Boot</div>
            <div onclick="showQuestions('spring-security')">Spring Security</div>
        </div>
    </div>

    <!-- Questions Section -->
    <div id="java-basic-questions" class="questions">
        <h3>Java Basic Questions</h3>
        <div class="question">1. What is JVM?</div>
        <div class="question">2. Difference between JDK, JRE, and JVM?</div>
        <div class="question">3. Explain the concept of OOP in Java.</div>
    </div>

    <div id="java-core-questions" class="questions">
        <h3>Java Core Questions</h3>
        <div class="question">1. Explain HashMap in Java.</div>
        <div class="question">2. What are threads in Java?</div>
        <div class="question">3. Difference between ArrayList and LinkedList?</div>
    </div>

    <div id="java-advanced-questions" class="questions">
        <h3>Java Advanced Questions</h3>
        <div class="question">1. What is Hibernate?</div>
        <div class="question">2. Explain Java Streams API.</div>
        <div class="question">3. What are design patterns in Java?</div>
    </div>

    <div id="java-logic-questions" class="questions">
        <h3>Java Code Logic Questions</h3>
        <div class="question">1. Write a program to reverse a string in Java.</div>
        <div class="question">2. Find factorial using recursion.</div>
        <div class="question">3. Check if a number is prime.</div>
    </div>

    <div id="angular-basic-questions" class="questions">
        <h3>Angular Basic Questions</h3>
        <div class="question">1. What is Angular?</div>
        <div class="question">2. Difference between AngularJS and Angular?</div>
        <div class="question">3. What are Angular directives?</div>
    </div>

    <div id="spring-core-questions" class="questions">
        <h3>Spring Core Questions</h3>
        <div class="question">1. What is Spring Framework?</div>
        <div class="question">2. Explain Dependency Injection.</div>
        <div class="question">3. What are Spring beans?</div>
    </div>
</body>
</html>
