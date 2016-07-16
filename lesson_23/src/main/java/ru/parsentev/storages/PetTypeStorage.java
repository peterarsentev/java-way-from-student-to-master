package ru.parsentev.storages;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Pet;
import ru.parsentev.models.PetType;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

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
public class PetTypeStorage {
    private static final Logger log = LoggerFactory.getLogger(PetTypeStorage.class);
    private static final PetTypeStorage instance = new PetTypeStorage();
    private final SessionFactory factory = HibernateFactory.getFactory();

    private PetTypeStorage() {
    }

    public static PetTypeStorage getInstance() {
        return instance;
    }

    public PetType add(PetType type) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(type);
            session.getTransaction().commit();
        }
        return type;
    }

    public Optional<PetType> findById(int id) {
        Optional<PetType> result = Optional.empty();
        try(Session session = factory.openSession()) {
            PetType type = session.get(PetType.class, id);
            if (type != null) {
                result = Optional.of(type);
            }
        }
        return result;
   }

    public List<PetType> getAll() {
        List<PetType> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            result.addAll(session.createQuery("from PetType").list());
        }
        return result;
    }
}
