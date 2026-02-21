package controller;

import dao.FormationDAO;
import model.Formation;

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
 * Controller for Step 2 – Education (Formation).
 * GET: show form; POST: save to session list, persist via FormationDAO, redirect to step 3.
 */
@WebServlet("/formation")
public class FormationServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/formation.jsp";
    private static final String SESSION_FORMATIONS = "formations";

    private final FormationDAO formationDAO = new FormationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String degree = trim(request.getParameter("degree"));
        String institution = trim(request.getParameter("institution"));
        String startYear = trim(request.getParameter("startYear"));
        String endYear = trim(request.getParameter("endYear"));

        Formation f = new Formation();
        f.setDegree(degree);
        f.setInstitution(institution);
        f.setStartYear(startYear);
        f.setEndYear(endYear);

        HttpSession session = request.getSession(true);
        @SuppressWarnings("unchecked")
        List<Formation> list = (List<Formation>) session.getAttribute(SESSION_FORMATIONS);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(f);
        session.setAttribute(SESSION_FORMATIONS, list);

        try {
            formationDAO.save(list, session.getId());
        } catch (Exception e) {
            request.setAttribute("error", "Could not save data. Please try again.");
            request.getRequestDispatcher(VIEW).forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/experience");
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }
}
