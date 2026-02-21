#  CV Generator – Java Web (MVC & DAO)

##  Project Overview
This project is a **Java Web application** that automatically generates a **Curriculum Vitae (CV)** based on user-provided information.  
The application follows a **multi-step form process** and is built using the **MVC (Model–View–Controller)** architecture combined with the **DAO (Data Access Object)** pattern to ensure clean separation of concerns and maintainable code.



##  Features
- Step-by-step CV creation process
- Data persistence using **HTTP Session**
- File-based storage via **DAO pattern**
- Clear separation of layers using **MVC**
- Final CV display in a dedicated space



##  Application Workflow

The CV generation process is divided into **four main steps**:

### 1. Personal Information (État Civil)
- A **JSP page** displays a form for personal details.
- A **Servlet controller**:
  - Retrieves form data
  - Stores data in the HTTP session
  - Persists data using `EtatCivilDAO`
  - Redirects to the next step

### 2. Education (Formation)
- User fills in education details via JSP.
- Data is processed by a servlet controller.
- Information is saved in session and file storage using `FormationDAO`.

### 3. Professional Experience
- Experience details are collected through a form.
- A dedicated servlet handles validation and storage.
- Data is persisted using `ExperienceDAO`.

### 4. Hobbies & Skills
- Final step for hobbies and additional skills.
- Data is saved and the user is redirected to the CV preview page.

---

##  CV Space
At the end of the process, the user is redirected to a **CV space** where:
- All collected data is retrieved from the session
- The complete CV is dynamically generated and displayed using JSP



##  Architecture

### MVC
- **Model**: JavaBeans representing CV entities (Personal Info, Education, Experience, Hobbies)
- **View**: JSP pages for data input and CV display
- **Controller**: Servlets handling requests and navigation logic

### DAO
- Each CV section has its own DAO class
- DAOs handle file-based data persistence
- Ensures loose coupling between business logic and data access



##  Technologies Used
- Java EE (Servlets, JSP)
- MVC Architecture
- DAO Pattern
- HTTP Sessions
- File I/O
- HTML / CSS


webapp/
 └── views/
