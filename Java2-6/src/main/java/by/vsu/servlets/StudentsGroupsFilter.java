package by.vsu.servlets;

import java.io.IOException;

import by.vsu.database.models.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({
        "/students/save.html",
        "/students/delete.html",
        "/students/edit.html",
        "/groups/edit.html",
        "/groups/delete.html",
        "/groups/save.html"
})
public class StudentsGroupsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession session = httpReq.getSession(false);

        if (session != null) {
            Object userAttr = session.getAttribute("user");

            if (userAttr instanceof User user) {
                if (user.getRole().equals("dean")) {
                    chain.doFilter(req, resp);
                    return;
                }

                httpResp.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещен. Только для dean!");
                return;
            }
        }

        httpResp.sendRedirect(httpReq.getContextPath() + "/login-form.jsp");
    }
}