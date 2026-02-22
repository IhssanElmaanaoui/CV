<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step 3 – Professional Experience</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="wizard-page">
    <main class="wizard-card">
        <header class="wizard-header">
            <span class="step-badge">Step 3 of 4</span>
            <h1>Professional Experience</h1>
            <p>Add your job title, company and description.</p>
        </header>

        <c:if test="${not empty error}">
            <p class="error-msg">${error}</p>
        </c:if>

        <c:if test="${not empty experiences}">
            <p class="added-list">Added: <strong>${experiences.size()}</strong> experience(s) – Stage and/or Jobs.</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/experience" method="post" class="wizard-form">
            <label>Type</label>
            <div class="radio-group">
                <label class="radio-label"><input type="radio" name="type" value="job" checked> Job</label>
                <label class="radio-label"><input type="radio" name="type" value="stage"> Stage (internship)</label>
            </div>

            <label for="jobTitle">Job title</label>
            <input type="text" id="jobTitle" name="jobTitle" placeholder="e.g. Software Engineer">

            <label for="company">Company</label>
            <input type="text" id="company" name="company" placeholder="e.g. Acme Inc.">

            <label for="startDate">Start date</label>
            <input type="text" id="startDate" name="startDate" placeholder="e.g. Jan 2020 or 2020">

            <label for="endDate">End date</label>
            <input type="text" id="endDate" name="endDate" placeholder="e.g. Present or Dec 2023">

            <label for="description">Description</label>
            <textarea id="description" name="description" rows="4" placeholder="Key responsibilities and achievements..."></textarea>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/formation" class="btn btn-secondary">Back</a>
                <button type="submit" name="action" value="add" class="btn btn-secondary">Add experience</button>
                <button type="submit" name="action" value="next" class="btn btn-primary">Next – Skills & Hobbies</button>
            </div>
        </form>
    </main>
</body>
</html>
