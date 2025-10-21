<!DOCTYPE html>
<html>
<head>
    <title>Java Test</title>
</head>
<body>
    <h2>Online Exam</h2>

    <!-- Button or clickable div -->
    <div onclick="loadQuestions()">Java Basic</div>

    <!-- Container for questions -->
    <div id="questions"></div>

    <script>
    function loadQuestions() {
        fetch("java_basic_questions.json") // Make sure this file is in WebContent folder
            .then(response => response.json())
            .then(data => {
                let container = document.getElementById("questions");
                container.innerHTML = ""; // clear old questions

                data.forEach((q, index) => {
                    container.innerHTML += `
                        <div class="question">
                            <p>${index + 1}. ${q.question}</p>
                            ${q.options.map(opt => `
                                <label>
                                    <input type="radio" name="q${q.id}" value="${opt}"> ${opt}
                                </label><br>
                            `).join("")}
                        </div>
                    `;
                });
            })
            .catch(err => console.error("Error loading questions:", err));
    }
    </script>
</body>
</html>
