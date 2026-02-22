package controller;

import dao.ExperienceDAO;
import model.Experience;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for Step 3 – Professional Experience.
 * GET: show form; POST: save to session list, persist via ExperienceDAO, redirect to step 4.
 */
@WebServlet("/experience")
public class ExperienceServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/experience.jsp";
    private static final String SESSION_EXPERIENCES = "experiences";

    private final ExperienceDAO experienceDAO = new ExperienceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        @SuppressWarnings("unchecked")
        List<Experience> list = (List<Experience>) session.getAttribute(SESSION_EXPERIENCES);
        if (list == null) list = new ArrayList<>();
        request.setAttribute(SESSION_EXPERIENCES, list);
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = trim(request.getParameter("action"));
        String type = trim(request.getParameter("type"));
        String jobTitle = trim(request.getParameter("jobTitle"));
        String company = trim(request.getParameter("company"));
        String startDate = trim(request.getParameter("startDate"));
        String endDate = trim(request.getParameter("endDate"));
        String description = trim(request.getParameter("description"));

        HttpSession session = request.getSession(true);
        @SuppressWarnings("unchecked")
        List<Experience> list = (List<Experience>) session.getAttribute(SESSION_EXPERIENCES);
        if (list == null) list = new ArrayList<>();

        if (jobTitle != null || company != null) {
            Experience ex = new Experience();
            ex.setType(type != null && "stage".equalsIgnoreCase(type) ? "stage" : "job");
            ex.setJobTitle(jobTitle);
            ex.setCompany(company);
            ex.setStartDate(startDate);
            ex.setEndDate(endDate);
            ex.setDescription(description);
            list.add(ex);
            session.setAttribute(SESSION_EXPERIENCES, list);
            try {
                experienceDAO.save(list, session.getId());
            } catch (Exception e) {
                request.setAttribute("error", "Could not save data. Please try again.");
                request.setAttribute(SESSION_EXPERIENCES, list);
                request.getRequestDispatcher(VIEW).forward(request, response);
                return;
            }
        }

        if ("add".equals(action)) {
            response.sendRedirect(request.getContextPath() + "/experience");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/skills");
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }
}
