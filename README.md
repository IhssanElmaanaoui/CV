# CV Generator – Java Web (MVC & DAO)

## Project Overview

This project is a **Java Web application** that automatically generates a **Curriculum Vitae (CV)** based on user-provided information. The application follows a **multi-step wizard** and uses **MVC** with **DAO** and **file-based persistence** (no database).

## Features

- **Wizard-style flow**: Step 1 (Personal Info) → Step 2 (Education) → Step 3 (Experience) → Step 4 (Skills & Hobbies) → CV Preview
- **HTTP Session** for in-memory data during the flow
- **File-based persistence** via DAO (one DAO per section; files under `user.home/cvdata/session_<id>/`)
- **ATS-friendly CV**: Semantic HTML (h1, h2, section, article), one-column layout, no tables/icons/images, printable A4
- **JSTL & EL only** in JSPs (no scriptlets)

## Project Structure

```
src/main/java/
 ├── controller/     # Servlets (EtatCivil, Formation, Experience, Skills, CvPreview)
 ├── model/         # JavaBeans (EtatCivil, Formation, Experience, SkillsHobbies)
 ├── dao/            # File-based DAOs
 └── util/           # FileStorageUtil

webapp/
 ├── index.jsp       # Entry: "Start" → /etatcivil
 ├── WEB-INF/
 │   ├── web.xml     # Servlet mappings, welcome file
 │   └── views/      # JSPs (etatcivil, formation, experience, skills, cv)
 └── assets/
     └── style.css   # Wizard + CV styles (responsive, print-friendly)
```

## Build & Run (Apache Tomcat)

1. **Build WAR**
   ```bash
   mvn clean package
   ```
2. **Deploy** the generated `target/cv-generator.war` to Tomcat (e.g. copy to `CATALINA_HOME/webapps/`).
3. **Open**  
   `http://localhost:8080/cv-generator/`  
   (or your context path) and click **Start** to begin the wizard.

## Application Workflow

| Step | URL        | Form fields | Persistence   | Next        |
|------|------------|-------------|---------------|-------------|
| 1    | `/etatcivil`  | First/last name, email, phone, address, LinkedIn, professional summary | EtatCivilDAO  | `/formation`  |
| 2    | `/formation`  | Degree, institution, start/end year | FormationDAO  | `/experience` |
| 3    | `/experience` | Job title, company, start/end date, description | ExperienceDAO | `/skills`     |
| 4    | `/skills`     | Technical skills, soft skills, hobbies | SkillsDAO     | `/cv`         |
| –    | `/cv`         | CV preview (read-only) | Session only  | –            |

## Architecture

- **Model**: JavaBeans for each CV section (getters/setters, serializable).
- **View**: JSPs under `WEB-INF/views/`; display and EL only, no business logic.
- **Controller**: One servlet per step; GET → forward to JSP, POST → validate, session, DAO save, redirect.
- **DAO**: One DAO per entity; reads/writes `.properties` files in `FileStorageUtil.getSessionStorageDir(sessionId)`.

## Technologies

- Java 11, Java EE (Servlets 4.0, JSP, JSTL 1.2)
- MVC, DAO pattern, HTTP Session
- File I/O (no database)
- HTML5, CSS3 (no frameworks)
