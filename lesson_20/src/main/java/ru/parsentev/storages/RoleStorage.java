package ru.parsentev.storages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private RoleStorage() {
    }

    public static RoleStorage getInstance() {
        return instance;
    }

    public Role add(final Role role) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into roles (name) values (?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    role.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
        return role;
    }

    public Optional<Role> findById(final int id) {
        Optional<Role> result = Optional.empty();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "select * from roles where id=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    result = Optional.of(role);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }

    public List<Role> getAll() {
        final List<Role> roles = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from roles")) {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        } catch (final SQLException e) {
            log.error("Error occurred in getting list of users", e);
        }
        return roles;
    }
}
