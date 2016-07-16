package ru.parsentev.storages;

import org.junit.Test;
import ru.parsentev.models.Role;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 14.07.2016
 */
public class RoleStorageTest {

    private final RoleStorage roles = RoleStorage.getInstance();

    @Test
    public void testAdd() throws Exception {
        Role role = new Role();
        role.setName("test");
        role = this.roles.add(role);
        assertThat(role, is(this.roles.findById(role.getId()).get()));
    }

    @Test
    public void testGetAll() throws Exception {
        Role role = new Role();
        role.setName("test");
        role = this.roles.add(role);
        assertTrue(this.roles.getAll().contains(role));
    }
}