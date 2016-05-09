package ru.parsentev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author parsentev
 * @since 09.05.2016
 */
public class UserServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

	private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final String key = req.getParameter("key");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User user : this.users) {
            if (key == null || user.getName().contains(key)) {
                writer.append(user.toString());
            }
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.users.add(this.extractUser(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final User update = this.extractUser(req);
        Iterator<User> it = this.users.iterator();
        while (it.hasNext()) {
            final User user = it.next();
            if (user.getId() == update.getId()) {
                update.setCreated(user.getCreated());
                it.remove();
                break;
            }
        }
        this.users.add(update);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Iterator<User> it = this.users.iterator();
        while (it.hasNext()) {
            final User user = it.next();
            if (user.getId() == id) {
                it.remove();
                break;
            }
        }
    }

    private User extractUser(HttpServletRequest req) {
        final User user = new User();
        user.setId(Integer.valueOf(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setHeigh(Float.valueOf(req.getParameter("heigh")));
        user.setChildren(req.getParameterValues("children"));
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(req.getParameter("create")));
        user.setCreated(calendar);
        return user;
    }
}
