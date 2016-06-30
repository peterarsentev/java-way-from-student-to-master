package ru.parsentev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
