package controller;

import util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Login: GET shows form, POST validates and redirects to wizard.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/etat-civil");
            return;
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (AuthUtil.validate(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", username == null ? null : username.trim());
            response.sendRedirect(request.getContextPath() + "/etat-civil");
            return;
        }

        request.setAttribute("error", "Invalid username or password.");
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
