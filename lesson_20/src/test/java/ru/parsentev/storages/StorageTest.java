package ru.parsentev.storages;

import org.junit.Ignore;
import org.junit.Test;
import ru.parsentev.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: make hbsql memory tests
 *
 * @author parsentev
 * @since 28.06.2016
 */
@Ignore
public class StorageTest {
    private final String url = "jdbc:postgresql://127.0.0.1:5432/clinic";
    private final String username = "postgres";
    private final String password = "password";

    @Test
    public void create() {
        final Storage storage = new Storage(this.url, this.username, this.password);
        User user = storage.create(new User("Petr Arsentev"));
        assertThat(user, is(storage.findById(user.getId())));
    }

    @Test
    public void update() {
        final Storage storage = new Storage(this.url, this.username, this.password);
        User user = storage.create(new User("Petr Arsentev"));
        user.setName("Petr");
        storage.update(user);
        assertThat(user.getName(), is(storage.findById(user.getId()).getName()));
    }


    @Test(expected = IllegalStateException.class)
    public void delete() {
        final Storage storage = new Storage(this.url, this.username, this.password);
        User user = storage.create(new User("Petr Arsentev"));
        storage.delete(user.getId());
        storage.findById(user.getId());
    }
}