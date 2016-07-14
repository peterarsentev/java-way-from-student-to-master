package ru.parsentev.servlets;

import org.junit.Test;
import ru.parsentev.models.User;
import ru.parsentev.storages.UserStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author parsentev
 * @since 27.06.2016
 */
public class UserActionsTest {
    @Test
    public void whenExecutePostShouldCreateUser()
            throws ServletException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        User user = new User();
        user.setUsername("Petr");
        user.setFullname("Arsentev");
        UserActions actions = new UserActions();
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("name")).thenReturn(user.getUsername());
        when(req.getParameter("login")).thenReturn(user.getFullname());
        HttpServletResponse resp = mock(HttpServletResponse.class);
        actions.doPost(req, resp);
        assertThat(
                UserStorage.getInstance().getAll().iterator().next(),
                is(user)
        );
    }

    @Test
    public void whenExecuteGetShouldReturnView() throws IOException, ServletException {
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getRequestDispatcher("/WEB-INF/views/index.jsp")).thenReturn(rd);
        UserActions actions = new UserActions();
        actions.doGet(req, resp);
        verify(rd).forward(req, resp);
    }
}