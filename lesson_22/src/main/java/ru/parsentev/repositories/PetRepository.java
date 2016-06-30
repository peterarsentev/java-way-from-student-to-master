package ru.parsentev.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import ru.parsentev.models.Pet;
import ru.parsentev.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 28.06.2016
 */
public class PetRepository {
    private static final Logger LOG = getLogger(PetRepository.class);

    private final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    public List<Pet> values() {
        List<Pet> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            result.addAll(session.createQuery("from Pet").list());
            session.getTransaction().commit();
        }
        return result;
    }

    public Pet create(final Pet user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    public void update(final Pet user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public void delete(int id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(new Pet(id));
            session.getTransaction().commit();
        }
    }

    public Pet findById(int id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Pet user = session.get(Pet.class, id);
            if (user == null) {
                throw new IllegalStateException(String.format("User %s does not exists", id));
            }
            session.getTransaction().commit();
            return user;
        }
    }
}
