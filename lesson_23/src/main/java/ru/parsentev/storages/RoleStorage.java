package ru.parsentev.storages;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.PetType;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 11.07.2016
 */
public class RoleStorage {
    private static final Logger log = LoggerFactory.getLogger(RoleStorage.class);
    private static final RoleStorage instance = new RoleStorage();
    private final SessionFactory factory = HibernateFactory.getFactory();

    private RoleStorage() {
    }

    public static RoleStorage getInstance() {
        return instance;
    }

    public Role add(final Role role) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        }
        return role;
    }

    public Optional<Role> findById(final int id) {
        Optional<Role> result = Optional.empty();
        try(Session session = factory.openSession()) {
            Role role = session.get(Role.class, id);
            if (role != null) {
                result = Optional.of(role);
            }
        }
        return result;
    }

    public List<Role> getAll() {
        List<Role> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            result.addAll(session.createQuery("from Role").list());
        }
        return result;
    }
}
