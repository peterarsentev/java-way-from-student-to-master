package ru.parsentev.storages;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author parsentev
 * @since 28.06.2016
 */
public class StorageTest extends DbInit {
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);
    private final Storage storage = new Storage(Pool.getDataSource());
    private final RoleStorage roles = RoleStorage.getInstance();

    @Test
    public void create() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        this.roles.add(role);
        User user = new User();
        user.setUsername("Petr Arsentev");
        user.setRole(role);
        user = storage.create(user);
        assertThat(user, is(storage.findById(user.getId())));
    }

    @Test
    public void update() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        this.roles.add(role);
        User user = new User();
        user.setUsername("Petr Arsentev");
        user.setRole(role);
        user = storage.create(user);
        user.setUsername("Petr");
        storage.update(user);
        assertThat(user.getUsername(), is(storage.findById(user.getId()).getUsername()));
    }


    @Test(expected = IllegalStateException.class)
    public void delete() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        this.roles.add(role);
        User user = new User();
        user.setUsername("Petr Arsentev");
        user.setRole(role);
        user = storage.create(user);
        storage.delete(user.getId());
        storage.findById(user.getId());
    }
}