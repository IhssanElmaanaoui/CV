package controller;

import model.EtatCivil;
import model.Experience;
import model.Formation;
import model.SkillsHobbies;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for CV Preview (final page).
 * Reads all data from session and forwards to CV JSP.
 * No business logic in view; model attributes only.
 */
public class CvPreviewServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/cv.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/etatcivil");
            return;
        }

        EtatCivil etatCivil = (EtatCivil) session.getAttribute("etatCivil");
        if (etatCivil == null) {
            response.sendRedirect(request.getContextPath() + "/etatcivil");
            return;
        }
        @SuppressWarnings("unchecked")
        List<Formation> formations = (List<Formation>) session.getAttribute("formations");
        @SuppressWarnings("unchecked")
        List<Experience> experiences = (List<Experience>) session.getAttribute("experiences");
        SkillsHobbies skillsHobbies = (SkillsHobbies) session.getAttribute("skillsHobbies");

        if (formations == null) formations = new ArrayList<>();
        if (experiences == null) experiences = new ArrayList<>();

        request.setAttribute("etatCivil", etatCivil);
        request.setAttribute("formations", formations);
        request.setAttribute("experiences", experiences);
        request.setAttribute("skillsHobbies", skillsHobbies);

        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
