package ru.parsentev.servlets;

import org.slf4j.Logger;
import ru.parsentev.models.User;
import ru.parsentev.storages.RoleStorage;
import ru.parsentev.storages.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 11.07.2016
 */
public class UserAddServlet extends HttpServlet {
    private static final Logger log = getLogger(UserAddServlet.class);
    private final RoleStorage roles = RoleStorage.getInstance();
    private final UserStorage users = UserStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("roles", this.roles.getAll());
        req.getRequestDispatcher("/WEB-INF/views/users/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setFullname(req.getParameter("fullname"));
        user.setPhone(req.getParameter("phone"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setEnabled(Boolean.valueOf(req.getParameter("enabled")));
        user.setRole(this.roles.findById(Integer.valueOf(req.getParameter("role.id"))).get());
        this.users.add(user);
        resp.sendRedirect(String.format("%s/users.do", req.getContextPath()));
    }
}
