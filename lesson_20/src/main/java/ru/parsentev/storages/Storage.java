package ru.parsentev.storages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 28.06.2016
 */
public class Storage {
    private static final Logger LOG = getLogger(Storage.class);

    private final DataSource ds;

    public Storage(final DataSource ds) {
        this.ds = ds;
    }

    public List<User> values() {
        final List<User> users = new ArrayList<>();
        try (final Connection connection = this.ds.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from users")) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
        } catch (final SQLException e) {
            LOG.error("Error occurred in getting list of users", e);
        }
        return users;
    }

    public User create(final User user) {
        try (final Connection connection = this.ds.getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into users (username, role_id) values (?, ?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getRole().getId());
            statement.executeUpdate();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error occurred in creating user", e);
        }
        return user;
    }

    public void update(final User user) {
        try (final Connection connection = this.ds.getConnection();
             final PreparedStatement statement = connection.prepareStatement("update users set username = ?")) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error occurred in updating user", e);
        }
    }

    public void delete(int id) {
        try (final Connection connection = this.ds.getConnection();
             final PreparedStatement statement = connection.prepareStatement("delete from users where id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error occurred in updating user", e);
        }
    }

    public User findById(int id) {
        try (final Connection connection = this.ds.getConnection();
             final PreparedStatement statement = connection.prepareStatement("select * from users where id=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    return user;
                }
            }
        } catch (SQLException e) {
            LOG.error("Error occurred in getting user", e);
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }
}
