package ru.parsentev.storages;

import com.google.common.base.Joiner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.springframework.data.jpa.provider.HibernateUtils;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.sql.*;
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
    private static final UserStorage instance = new UserStorage();
    private final PetStorage pets = PetStorage.getInstance();
    private final SessionFactory factory = HibernateFactory.getFactory();

    private UserStorage() {
    }

    public static UserStorage getInstance() {
        return instance;
    }

    public User add(User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    public void update(User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            result.addAll(session.createQuery("from User as u join fetch u.role").list());
        }
        return result;
    }

    public Optional<User> findByCridentional(String username, String password) {
        Optional<User> result = Optional.empty();
        try(Session session = factory.openSession()) {
            Query query = session.createQuery(
                    "from User as u join fetch u.role where u.username=:username and u.password=:password"
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            result = query.uniqueResultOptional();
        }
        return result;
    }

    public Optional<User> findById(final int id) {
        Optional<User> result = Optional.empty();
        try(Session session = factory.openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                result = Optional.of(user);
            }
        }
        return result;
    }

    public void delete(int id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(new User(id));
            session.getTransaction().commit();
        }
    }
}
