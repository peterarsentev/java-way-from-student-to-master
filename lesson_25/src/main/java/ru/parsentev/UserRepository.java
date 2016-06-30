package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 21.06.2016
 */
//@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User> {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcTemplate template;

    @Autowired
    public UserRepository(final JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public User save(final User model) {
        final String INSERT_SQL = "insert into users (name) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.template.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                        ps.setString(1, model.getName());
                        return ps;
                    }
                },
                keyHolder);
        model.setId(keyHolder.getKey().intValue());
        return model;
    }


    @Override
    public List<User> getAll() {
        return this.template.query("select * from users", new BeanPropertyRowMapper(User.class));
    }
}
