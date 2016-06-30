package ru.parsentev.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 28.06.2016
 */
public class UserRepository {
    private static final Logger LOG = getLogger(UserRepository.class);

    private final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    public List<User> values() {
        List<User> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            result.addAll(session.createQuery("from User").list());
            session.getTransaction().commit();
        }
        return result;
    }

    public User create(final User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    public void update(final User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public void delete(int id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(new User(id));
            session.getTransaction().commit();
        }
    }

    public User findById(int id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user == null) {
                throw new IllegalStateException(String.format("User %s does not exists", id));
            }
            session.getTransaction().commit();
            return user;
        }
    }
}
