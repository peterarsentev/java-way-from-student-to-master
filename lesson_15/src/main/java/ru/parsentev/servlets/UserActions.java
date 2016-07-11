package ru.parsentev.servlets;

import com.google.common.base.Joiner;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 27.06.2016
 */
public class UserActions extends HttpServlet {
    private static final Logger log = getLogger(UserActions.class);
    private final UserStorage storage = UserStorage.getInstance();
    private final AtomicInteger ids = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        String method = req.getParameter("method");
        if ("delete".equals(method)) {
            this.storage.delete(Integer.valueOf(req.getParameter("id")));
        }
        out.append(
                Joiner.on("")
                        .join("<!DOCTYPE html>\n",
                                "<html lang=\"en\">\n",
                                "<head>\n",
                                "    <meta charset=\"UTF-8\">\n",
                                "    <title></title>\n",
                                "</head>\n",
                                "<body>\n",
                                "<form action=\"\" method=\"GET\">\n",
                                "    <input type=\"text\" name=\"key\">\n",
                                "    <input type=\"submit\" name=\"name\" value=\"Search\">\n",
                                "</form><br/>\n",
                                "<table cellpadding=\"0\" cellspacing=\"0\" border=\"1\">\n",
                                "    <tr>\n",
                                "        <th>Name</th>\n",
                                "        <th>Age</th>\n",
                                "        <th>Date birthday</th>\n",
                                "        <th>Active</th>\n",
                                "        <th>Action</th>\n",
                                "    </tr>\n",
                                this.buildTable(req.getParameter("key")),
                                "</table><br/>\n",
                               this.buildForm(this.storage.findById(req.getParameter("id"))),
                                "</form>\n",
                                "</body>\n",
                                "</html>"
                        )
        );
        out.flush();
    }

    private String buildTable(String key) {
        List<String> rows = new ArrayList<>();
        for (User user : storage.getAll()) {
            if (key == null || (key != null && user.getName().contains(key))) {
                rows.addAll(Arrays.asList(
                                "    <tr>\n",
                                "        <td>", user.getName(), "</td>\n",
                                "        <td>", String.valueOf(user.getAge()), "</td>\n",
                                "        <td>", String.valueOf(user.getBirthday().getTime()), "</td>\n",
                                "        <td>", String.valueOf(user.isActive()), "</td>\n",
                                "        <td><a href='?method=update&id=",
                                String.valueOf(user.getId()), "'>edit</a> ",
                                        "<a href='?method=delete&id=",
                                String.valueOf(user.getId()), "'>delete</a></td>\n",
                                "    </tr>\n"
                        )
                );
            }
        }
        return Joiner.on("").join(rows);
    }

    private String buildForm(User user) {
        return Joiner.on("").skipNulls().join(
                "<form action=\"\" method=\"POST\">\n",
                user.getId() != -1 ? "    <input type=\"hidden\" name=\"method\" value=\"update\"><br/>\n" : "",
                user.getId() != -1 ? String.format("<input type=\"hidden\" name=\"id\" value=\"%s\"><br/>\n", user.getId()) : "",
                "    Name:<input type=\"text\" name=\"name\" value=\"", user.getName(), "\"><br/>\n",
                "    Age:<input type=\"text\" name=\"age\" value=\"", user.getAge(), "\"><br/>\n",
                "    Date birthday:<input type=\"date\" name=\"birthday\" value=\"", user.getBirthday(), "\"><br/>\n",
                "    Active:<input type=\"checkbox\" name=\"active\" value=\"", user.isActive(), "\"><br/>\n",
                "    <input type=\"submit\" name=\"name\">\n",
                "</form>\n"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            boolean update = "update".equals(req.getParameter("method"));
            Calendar cal = Calendar.getInstance();
            cal.setTime(
                    new SimpleDateFormat("yyyy-MM-dd")
                            .parse(req.getParameter("birthday"))
            );
            User user = new User(
                    update ? Integer.valueOf(req.getParameter("id")) : ids.incrementAndGet(),
                    req.getParameter("name"),
                    Integer.valueOf(req.getParameter("age")),
                    cal,
                    "on".equals(req.getParameter("active"))
            );
            if (update) {
                this.storage.update(user);
            } else {
                this.storage.add(user);
            }
        } catch (Exception e) {
            log.error("Error", e);
        }
        resp.sendRedirect(String.format("%s/users.do", req.getContextPath()));
    }
}