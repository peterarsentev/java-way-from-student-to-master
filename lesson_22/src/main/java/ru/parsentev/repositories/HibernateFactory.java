package ru.parsentev.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 16.07.2016
 */
public class HibernateFactory {
    private static final Logger log = LoggerFactory.getLogger(HibernateFactory.class);
    private static final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    private HibernateFactory() {
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
