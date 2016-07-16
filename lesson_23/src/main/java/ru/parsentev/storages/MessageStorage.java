package ru.parsentev.storages;

import com.google.common.base.Joiner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Message;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 11.07.2016
 */
public class MessageStorage {
    private static final Logger log = LoggerFactory.getLogger(MessageStorage.class);
    private static final MessageStorage instance = new MessageStorage();
    private final SessionFactory factory = HibernateFactory.getFactory();

    private MessageStorage() {
    }

    public static MessageStorage getInstance() {
        return instance;
    }

    public Message add(Message message) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
        }
        return message;
    }

    public List<Message> findByOwner(final int id) {
        List<Message> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            Query query = session.createQuery(
                    "select m from Message as m join fetch m.author where m.owner.id=:id"
            );
            query.setParameter("id", id);
            result = query.list();
        }
        return result;
    }


    public void deleteByOwner(int id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete from Message as m where m.owner.id=:id"
            );
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void delete(int messageId) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(new Message(messageId));
            session.getTransaction().commit();
        }
    }
}
