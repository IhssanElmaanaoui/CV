package controller;

import dao.EtatCivilDAO;
import model.EtatCivil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller for Step 1 – Personal Information (État Civil).
 * GET: show form; POST: validate, store in session, persist via DAO, redirect to step 2.
 */
public class EtatCivilServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/etatcivil.jsp";
    private static final String SESSION_ETAT_CIVIL = "etatCivil";

    private final EtatCivilDAO etatCivilDAO = new EtatCivilDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        EtatCivil existing = (EtatCivil) session.getAttribute(SESSION_ETAT_CIVIL);
        if (existing != null) {
            request.setAttribute(SESSION_ETAT_CIVIL, existing);
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String firstName = trim(request.getParameter("firstName"));
        String lastName = trim(request.getParameter("lastName"));
        String email = trim(request.getParameter("email"));
        String phone = trim(request.getParameter("phone"));
        String address = trim(request.getParameter("address"));
        String linkedIn = trim(request.getParameter("linkedIn"));
        String professionalSummary = trim(request.getParameter("professionalSummary"));

        // Validation: required fields
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            request.setAttribute("error", "First name and last name are required.");
            EtatCivil back = new EtatCivil();
            back.setFirstName(firstName);
            back.setLastName(lastName);
            back.setEmail(email);
            back.setPhone(phone);
            back.setAddress(address);
            back.setLinkedIn(linkedIn);
            back.setProfessionalSummary(professionalSummary);
            request.setAttribute(SESSION_ETAT_CIVIL, back);
            request.getRequestDispatcher(VIEW).forward(request, response);
            return;
        }

        EtatCivil etatCivil = new EtatCivil();
        etatCivil.setFirstName(firstName);
        etatCivil.setLastName(lastName);
        etatCivil.setEmail(email);
        etatCivil.setPhone(phone);
        etatCivil.setAddress(address);
        etatCivil.setLinkedIn(linkedIn);
        etatCivil.setProfessionalSummary(professionalSummary);

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_ETAT_CIVIL, etatCivil);

        try {
            etatCivilDAO.save(etatCivil, session.getId());
        } catch (Exception e) {
            request.setAttribute("error", "Could not save data. Please try again.");
            request.setAttribute(SESSION_ETAT_CIVIL, etatCivil);
            request.getRequestDispatcher(VIEW).forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/formation");
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }
}
