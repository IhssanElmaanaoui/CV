<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login – CV Generator</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="wizard-page login-page">
    <main class="wizard-card login-card" style="width:min(560px,100%);max-width:560px;margin:0 auto;">
        <header class="wizard-header">
            <h1>Login</h1>
            <p>Sign in to create or edit your CV.</p>
        </header>

        <c:if test="${not empty error}">
            <p class="error-msg">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post" class="wizard-form">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required autofocus>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>
        </form>
    </main>
</body>
</html>
