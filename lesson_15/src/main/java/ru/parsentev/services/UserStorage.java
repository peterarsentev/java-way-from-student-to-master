package ru.parsentev.services;

import org.slf4j.Logger;
import ru.parsentev.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 27.06.2016
 */
public class UserStorage {
    private static final Logger log = getLogger(UserStorage.class);
    private static final UserStorage INSTANCE = new UserStorage();
    private final List<User> users = new ArrayList<User>();

    private UserStorage() {
    }

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public List<User> getAll() {
        return this.users;
    }

    public void delete(Integer id) {
        users.remove(new User(id, null, 0, null, true));
    }

    public void update(User user) {
        this.delete(user.getId());
        this.add(user);
    }

    public User findById(String value) {
        User user = new User(-1, null, 0, null, true);
        if (value != null) {
            int id = Integer.valueOf(value);
           for (User find : users) {
               if (find.getId() == id) {
                   user = find;
                   break;
               }
           }
        }
        return user;
    }
}
