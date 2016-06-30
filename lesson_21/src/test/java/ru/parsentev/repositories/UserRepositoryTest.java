package ru.parsentev.repositories;

import org.junit.Test;
import ru.parsentev.models.User;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 28.06.2016
 */
public class UserRepositoryTest {
    @Test
    public void create() {
        final UserRepository storage = new UserRepository();
        User user = storage.create(new User("Petr Arsentev", Calendar.getInstance()));
        assertThat(user, is(storage.findById(user.getId())));
    }

    @Test
    public void update() {
        final UserRepository storage = new UserRepository();
        User user = storage.create(new User("Petr Arsentev", Calendar.getInstance()));
        user.setName("Petr");
        storage.update(user);
        assertThat(user.getName(), is(storage.findById(user.getId()).getName()));
    }

    @Test(expected = IllegalStateException.class)
    public void delete() {
        final UserRepository storage = new UserRepository();
        User user = storage.create(new User("Petr Arsentev", Calendar.getInstance()));
        storage.delete(user.getId());
        storage.findById(user.getId());
    }
}