package ru.parsentev.storages;

import org.slf4j.Logger;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 27.06.2016
 */
public class UserStorage {
    private static final Logger log = getLogger(UserStorage.class);
    private static final UserStorage INSTANCE = new UserStorage();
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final AtomicInteger ids = new AtomicInteger(0);
    private final RoleStorage roles = RoleStorage.getInstance();
    private final MessageStorage messages = MessageStorage.getInstance();

    private UserStorage() {
        User root = new User();
        root.setId(ids.incrementAndGet());
        root.setUsername("root");
        root.setPassword("root");
        root.setRole(this.roles.findById(1).get());
        this.users.add(root);
    }

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public void update(User user) {
        this.users.remove(user);
        this.users.add(user);
    }

    public List<User> getAll() {
        return this.users;
    }

    public Optional<User> findByCridentional(String username, String password) {
        return this.users.stream().filter(
                user -> user.getUsername().equals(username) && user.getPassword().equals(password)
        ).findFirst();
    }

    public Optional<User> findById(final int id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public void delete(int id) {
        this.messages.deleteByOwner(id);
        this.users.remove(new User(id));
    }
}
