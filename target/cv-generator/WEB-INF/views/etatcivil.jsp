<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step 1 - Personal Information</title>
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
                <a href="${pageContext.request.contextPath}/etat-civil" class="side-link active">Personal Data</a>
                <a href="${pageContext.request.contextPath}/formation" class="side-link">Education</a>
                <a href="${pageContext.request.contextPath}/experience" class="side-link">Experience</a>
                <a href="${pageContext.request.contextPath}/skills" class="side-link">Hobbies</a>
            </nav>
        </aside>

        <section class="wizard-content">
            <div class="wizard-card">
                <header class="wizard-header">
                    <span class="step-badge">Step 1 of 4</span>
                    <h1>Personal Information</h1>
                    <p>Enter your contact details.</p>
                </header>

                <c:if test="${not empty error}">
                    <p class="error-msg">${error}</p>
                </c:if>

                <form action="${pageContext.request.contextPath}/etat-civil" method="post" class="wizard-form">
                    <label for="firstName">First name <span class="required">*</span></label>
                    <input type="text" id="firstName" name="firstName" value="${etatCivil.firstName}" required>

                    <label for="lastName">Last name <span class="required">*</span></label>
                    <input type="text" id="lastName" name="lastName" value="${etatCivil.lastName}" required>

                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${etatCivil.email}">

                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" value="${etatCivil.phone}">

                    <label for="address">Address</label>
                    <input type="text" id="address" name="address" value="${etatCivil.address}">

                    <label for="linkedIn">LinkedIn</label>
                    <input type="url" id="linkedIn" name="linkedIn" value="${etatCivil.linkedIn}" placeholder="https://linkedin.com/in/...">

                    <label for="professionalSummary">Professional summary (optional)</label>
                    <textarea id="professionalSummary" name="professionalSummary" rows="3" placeholder="Short paragraph about your profile and goals">${etatCivil.professionalSummary}</textarea>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Next - Education</button>
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
