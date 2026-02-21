<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step 2 – Education</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="wizard-page">
    <main class="wizard-card">
        <header class="wizard-header">
            <span class="step-badge">Step 2 of 4</span>
            <h1>Education</h1>
            <p>Add your degree and institution (Formation).</p>
        </header>

        <c:if test="${not empty error}">
            <p class="error-msg">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/formation" method="post" class="wizard-form">
            <label for="degree">Degree / Diploma</label>
            <input type="text" id="degree" name="degree" placeholder="e.g. Master of Science in Computer Science">

            <label for="institution">Institution</label>
            <input type="text" id="institution" name="institution" placeholder="e.g. University of ...">

            <label for="startYear">Start year</label>
            <input type="text" id="startYear" name="startYear" placeholder="e.g. 2018">

            <label for="endYear">End year</label>
            <input type="text" id="endYear" name="endYear" placeholder="e.g. 2022">

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/etat-civil" class="btn btn-secondary">Back</a>
                <button type="submit" class="btn btn-primary">Next – Experience</button>
            </div>
        </form>
    </main>
</body>
</html>
