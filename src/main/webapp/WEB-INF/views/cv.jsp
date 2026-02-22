<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CV – ${etatCivil.fullName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body class="cv-page">
    <div class="cv-actions">
        <a href="${pageContext.request.contextPath}/etat-civil" class="btn btn-secondary">Edit from start</a>
        <button type="button" class="btn btn-primary" onclick="window.print();">Print / Save as PDF</button>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
    </div>

    <article class="cv-document" id="cv-document">
        <header class="cv-header">
            <h1>${etatCivil.fullName}</h1>
            <section class="cv-contact" aria-label="Contact information">
                <c:if test="${not empty etatCivil.email}"><span>${etatCivil.email}</span></c:if>
                <c:if test="${not empty etatCivil.phone}"><span>${etatCivil.phone}</span></c:if>
                <c:if test="${not empty etatCivil.address}"><span>${etatCivil.address}</span></c:if>
                <c:if test="${not empty etatCivil.linkedIn}"><span>${etatCivil.linkedIn}</span></c:if>
            </section>
        </header>

        <c:if test="${not empty etatCivil.professionalSummary}">
            <section class="cv-section" aria-labelledby="summary-heading">
                <h2 id="summary-heading">Professional Summary</h2>
                <p>${etatCivil.professionalSummary}</p>
            </section>
        </c:if>

        <c:if test="${not empty formations}">
            <section class="cv-section" aria-labelledby="education-heading">
                <h2 id="education-heading">Education</h2>
                <c:forEach var="f" items="${formations}" varStatus="st">
                    <article class="cv-entry">
                        <h3>${f.degree}</h3>
                        <p class="cv-meta">${f.institution} <c:if test="${not empty f.yearRange}"> – ${f.yearRange}</c:if></p>
                    </article>
                </c:forEach>
            </section>
        </c:if>

        <c:if test="${not empty experiences}">
            <c:set var="hasStage" value="false"/>
            <c:set var="hasJob" value="false"/>
            <c:forEach var="ex" items="${experiences}">
                <c:if test="${ex.type == 'stage'}"><c:set var="hasStage" value="true"/></c:if>
                <c:if test="${ex.type != 'stage'}"><c:set var="hasJob" value="true"/></c:if>
            </c:forEach>
            <c:if test="${hasStage == true}">
                <section class="cv-section" aria-labelledby="stage-heading">
                    <h2 id="stage-heading">Stage (Internships)</h2>
                    <c:forEach var="ex" items="${experiences}">
                        <c:if test="${ex.type == 'stage'}">
                            <article class="cv-entry">
                                <h3>${ex.jobTitle}</h3>
                                <p class="cv-meta">${ex.company} <c:if test="${not empty ex.dateRange}"> – ${ex.dateRange}</c:if></p>
                                <c:if test="${not empty ex.description}"><p>${ex.description}</p></c:if>
                            </article>
                        </c:if>
                    </c:forEach>
                </section>
            </c:if>
            <c:if test="${hasJob == true}">
                <section class="cv-section" aria-labelledby="jobs-heading">
                    <h2 id="jobs-heading">Professional Experience (Jobs)</h2>
                    <c:forEach var="ex" items="${experiences}">
                        <c:if test="${ex.type != 'stage'}">
                            <article class="cv-entry">
                                <h3>${ex.jobTitle}</h3>
                                <p class="cv-meta">${ex.company} <c:if test="${not empty ex.dateRange}"> – ${ex.dateRange}</c:if></p>
                                <c:if test="${not empty ex.description}"><p>${ex.description}</p></c:if>
                            </article>
                        </c:if>
                    </c:forEach>
                </section>
            </c:if>
        </c:if>

        <c:if test="${not empty skillsHobbies and (not empty skillsHobbies.technicalSkills or not empty skillsHobbies.softSkills)}">
            <section class="cv-section" aria-labelledby="skills-heading">
                <h2 id="skills-heading">Skills</h2>
                <c:if test="${not empty skillsHobbies.technicalSkills}">
                    <p><strong>Technical:</strong> ${skillsHobbies.technicalSkills}</p>
                </c:if>
                <c:if test="${not empty skillsHobbies.softSkills}">
                    <p><strong>Soft skills:</strong> ${skillsHobbies.softSkills}</p>
                </c:if>
            </section>
        </c:if>

        <c:if test="${not empty skillsHobbies and not empty skillsHobbies.hobbies}">
            <section class="cv-section" aria-labelledby="hobbies-heading">
                <h2 id="hobbies-heading">Hobbies</h2>
                <p>${skillsHobbies.hobbies}</p>
            </section>
        </c:if>
    </article>
</body>
</html>
