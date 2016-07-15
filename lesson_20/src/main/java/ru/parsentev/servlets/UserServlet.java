package ru.parsentev.servlets;

import org.slf4j.Logger;
import ru.parsentev.models.User;
import ru.parsentev.storages.MessageStorage;
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
public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private final RoleStorage roles = RoleStorage.getInstance();
    private final UserStorage users = UserStorage.getInstance();
    private final MessageStorage messages = MessageStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = this.users.findById(Integer.valueOf(req.getParameter("id"))).get();
        req.setAttribute("user", user);
        req.setAttribute("messages", this.messages.findByOwner(user.getId()));
        req.getRequestDispatcher("/WEB-INF/views/users/user.jsp").forward(req, resp);
    }
}
