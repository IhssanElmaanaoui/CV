package controller;

import dao.SkillsDAO;
import model.SkillsHobbies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller for Step 4 – Skills & Hobbies.
 * GET: show form; POST: save to session, persist via SkillsDAO, redirect to CV preview.
 */
@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/skills.jsp";
    private static final String SESSION_SKILLS = "skillsHobbies";

    private final SkillsDAO skillsDAO = new SkillsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String technicalSkills = trim(request.getParameter("technicalSkills"));
        String softSkills = trim(request.getParameter("softSkills"));
        String hobbies = trim(request.getParameter("hobbies"));

        SkillsHobbies skills = new SkillsHobbies();
        skills.setTechnicalSkills(technicalSkills);
        skills.setSoftSkills(softSkills);
        skills.setHobbies(hobbies);

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_SKILLS, skills);

        try {
            skillsDAO.save(skills, session.getId());
        } catch (Exception e) {
            request.setAttribute("error", "Could not save data. Please try again.");
            request.getRequestDispatcher(VIEW).forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/cv");
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }
}
