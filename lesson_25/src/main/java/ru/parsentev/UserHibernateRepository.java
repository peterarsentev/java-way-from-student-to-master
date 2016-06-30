package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 21.06.2016
 */
@org.springframework.stereotype.Repository
public class UserHibernateRepository implements HibernateRepository {
    private static final Logger log = LoggerFactory.getLogger(UserHibernateRepository.class);

    private final HibernateTemplate template;

    @Autowired
    public UserHibernateRepository(final HibernateTemplate template) {
        this.template = template;
    }

    @Transactional
    @Override
    public User save(User model) {
        this.template.save(model);
        return model;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.template.find("from User");
    }
}
