<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step 4 - Skills & Hobbies</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="wizard-page">
    <main class="wizard-shell">
        <header class="wizard-topbar">
            <div class="top-pill"><span id="live-datetime"></span></div>
            <div class="top-title">CV Generator</div>
            <div class="top-pill">visits: ${sessionScope.visitCount != null ? sessionScope.visitCount : 1}</div>
        </header>

        <aside class="wizard-sidebar">
            <nav aria-label="CV sections">
                <a href="${pageContext.request.contextPath}/etat-civil" class="side-link">Personal Data</a>
                <a href="${pageContext.request.contextPath}/formation" class="side-link">Education</a>
                <a href="${pageContext.request.contextPath}/experience" class="side-link">Experience</a>
                <a href="${pageContext.request.contextPath}/skills" class="side-link active">Hobbies</a>
            </nav>
        </aside>

        <section class="wizard-content">
            <div class="wizard-card">
                <header class="wizard-header">
                    <span class="step-badge">Step 4 of 4</span>
                    <h1>Skills & Hobbies</h1>
                    <p>List your technical skills, soft skills and hobbies.</p>
                </header>

                <c:if test="${not empty error}">
                    <p class="error-msg">${error}</p>
                </c:if>

                <form action="${pageContext.request.contextPath}/skills" method="post" class="wizard-form">
                    <label for="technicalSkills">Technical skills</label>
                    <textarea id="technicalSkills" name="technicalSkills" rows="3" placeholder="e.g. Java, Spring, SQL, Git"></textarea>

                    <label for="softSkills">Soft skills</label>
                    <textarea id="softSkills" name="softSkills" rows="2" placeholder="e.g. Teamwork, Communication, Problem-solving"></textarea>

                    <label for="hobbies">Hobbies</label>
                    <textarea id="hobbies" name="hobbies" rows="2" placeholder="e.g. Reading, Open source, Sports"></textarea>

                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/experience" class="btn btn-secondary">Back</a>
                        <button type="submit" class="btn btn-primary">View my CV</button>
                    </div>
                </form>
            </div>
        </section>

        <footer class="wizard-logout">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
        </footer>
    </main>
    <script>
        (function () {
            var el = document.getElementById("live-datetime");
            if (!el) return;
            function update() {
                el.textContent = new Date().toLocaleString();
            }
            update();
            setInterval(update, 1000);
        })();
    </script>
</body>
</html>
