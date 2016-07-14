package ru.parsentev.servlets;

import org.slf4j.Logger;
import ru.parsentev.models.Role;
import ru.parsentev.storages.RoleStorage;

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
public class RoleAddServlet extends HttpServlet {
    private static final Logger log = getLogger(RoleAddServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = new Role();
        role.setName(req.getParameter("name"));
        RoleStorage.getInstance().add(role);
        resp.sendRedirect(String.format("%s/users.do", req.getContextPath()));
    }
}
