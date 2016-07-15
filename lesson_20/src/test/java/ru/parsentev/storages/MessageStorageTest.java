package ru.parsentev.storages;

import org.junit.Test;
import ru.parsentev.models.Message;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 14.07.2016
 */
public class MessageStorageTest extends DbInit {

    final UserStorage users = UserStorage.getInstance();
    final MessageStorage messages = MessageStorage.getInstance();
    final RoleStorage roles = RoleStorage.getInstance();

    @Test
      public void testAdd() throws Exception {
        User user = new User();
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        Message message = new Message();
        message.setAuthor(user);
        message.setOwner(user);
        message.setCreated(new Timestamp(System.currentTimeMillis()));
        this.messages.add(message);
        assertTrue(this.messages.findByOwner(user.getId()).contains(message));
    }

    @Test
    public void testDeleteByOwner() throws Exception {
        User user = new User();
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        Message message = new Message();
        message.setAuthor(user);
        message.setOwner(user);
        message.setCreated(new Timestamp(System.currentTimeMillis()));
        this.messages.add(message);
        this.messages.deleteByOwner(user.getId());
        assertTrue(this.messages.findByOwner(user.getId()).isEmpty());
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        Message message = new Message();
        message.setAuthor(user);
        message.setOwner(user);
        message.setCreated(new Timestamp(System.currentTimeMillis()));
        this.messages.add(message);
        this.messages.delete(message.getId());
        assertTrue(this.messages.findByOwner(user.getId()).isEmpty());
    }
}