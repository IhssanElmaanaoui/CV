<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step 2 - Education</title>
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
                <a href="${pageContext.request.contextPath}/formation" class="side-link active">Education</a>
                <a href="${pageContext.request.contextPath}/experience" class="side-link">Experience</a>
                <a href="${pageContext.request.contextPath}/skills" class="side-link">Hobbies</a>
            </nav>
        </aside>

        <section class="wizard-content">
            <div class="wizard-card">
                <header class="wizard-header">
                    <span class="step-badge">Step 2 of 4</span>
                    <h1>Education</h1>
                    <p>Add your degree and institution.</p>
                </header>

                <c:if test="${not empty error}">
                    <p class="error-msg">${error}</p>
                </c:if>

                <c:if test="${not empty formations}">
                    <p class="added-list">Added: <strong>${formations.size()}</strong> education item(s).</p>
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
                        <button type="submit" name="action" value="add" class="btn btn-secondary">Ajouter education</button>
                        <button type="submit" name="action" value="next" class="btn btn-primary">Next - Experience</button>
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
