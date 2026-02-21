<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step 4 – Skills & Hobbies</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="wizard-page">
    <main class="wizard-card">
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
    </main>
</body>
</html>
