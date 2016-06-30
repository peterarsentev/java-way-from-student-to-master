package ru.parsentev.storages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.User;

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

    private final String url;
    private final String username;
    private final String password;

    public Storage(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<User> values() {
        final List<User> users = new ArrayList<>();
        try (final Connection connection = DriverManager.getConnection(this.url, this.url, this.password);
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from users")) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), null));
            }
        } catch (final SQLException e) {
            LOG.error("Error occurred in getting list of users", e);
        }
        return users;
    }

    public User create(final User user) {
        try (final Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into users (name) values (?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, user.getName());
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
        try (final Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             final PreparedStatement statement = connection.prepareStatement("update users set name = ?")) {
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error occurred in updating user", e);
        }
    }

    public void delete(int id) {
        try (final Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             final PreparedStatement statement = connection.prepareStatement("delete from users where id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error occurred in updating user", e);
        }
    }

    public User findById(int id) {
        try (final Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             final PreparedStatement statement = connection.prepareStatement("select * from users where id=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), null);
                }
            }
        } catch (SQLException e) {
            LOG.error("Error occurred in getting user", e);
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }
}
