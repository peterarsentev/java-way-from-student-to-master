package ru.parsentev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.User;
import ru.parsentev.services.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 27.06.2016
 */
public class UserActions extends HttpServlet {
    private static final Logger log = getLogger(UserActions.class);

    private UserStorage storage = UserStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", this.storage.getAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(
                    new SimpleDateFormat("dd-MM-yyyy")
                            .parse(req.getParameter("create"))
            );
            this.storage.add(
                    new User(
                            req.getParameter("name"),
                            req.getParameter("login"),
                            cal
                    )
            );
        } catch (ParseException e) {
            log.error("", e);
        }
        resp.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}