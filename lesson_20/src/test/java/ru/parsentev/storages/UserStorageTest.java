package ru.parsentev.storages;

import org.junit.Test;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 14.07.2016
 */
public class UserStorageTest extends DbInit {
    final UserStorage users = UserStorage.getInstance();
    final RoleStorage roles = RoleStorage.getInstance();

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setUsername("parsentev");
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        assertThat(user, is(this.users.findById(user.getId()).get()));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setUsername("parsentev");
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        user.setUsername("Petr Arsentev");
        this.users.update(user);
        assertThat(this.users.findById(user.getId()).get().getUsername(), is(user.getUsername()));
    }

    @Test
    public void testGetAll() throws Exception {
        Role role = new Role();
        User user = new User();
        user.setUsername("parsentev");
        user.setRole(this.roles.add(role));
        user = this.users.add(user);
        assertTrue(this.users.getAll().contains(user));
    }

    @Test
    public void testFindByCridentional() throws Exception {
        User user = new User();
        user.setUsername("parsentev");
        user.setPassword("root");
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        assertThat(user, is(this.users.findByCridentional(user.getUsername(), user.getPassword()).get()));
    }

    @Test
    public void testFindById() throws Exception {
        User user = new User();
        user.setUsername("parsentev");
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        assertThat(user, is(this.users.findById(user.getId()).get()));
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setUsername("parsentev");
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        this.users.delete(user.getId());
        assertThat(this.users.findById(user.getId()), is(Optional.<User>empty()));
    }
}