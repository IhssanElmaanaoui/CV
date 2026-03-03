package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

/**
 * Protects all app URLs except /login, /logout and /assets.
 * Redirects to /login when user is not in session.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> COUNTED_PATHS = Set.of(
            "/",
            "/etat-civil",
            "/formation",
            "/experience",
            "/skills",
            "/cv"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        String contextPath = req.getContextPath();
        if (contextPath != null && !contextPath.isEmpty() && path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }
        if (path.isEmpty()) path = "/";
        // Normalize: remove trailing slash so /login/ matches /login
        path = path.replaceFirst("/+$", "");
        if (path.isEmpty()) path = "/";

        if ("/login".equals(path) || "/logout".equals(path) || path.startsWith("/assets/")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            if ("GET".equalsIgnoreCase(req.getMethod()) && COUNTED_PATHS.contains(path)) {
                Integer current = (Integer) session.getAttribute("visitCount");
                session.setAttribute("visitCount", current == null ? 1 : current + 1);
            }
            chain.doFilter(request, response);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
