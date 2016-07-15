package ru.parsentev.storages;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 27.06.2016
 */
public class UserStorage {
    private static final Logger log = getLogger(UserStorage.class);
    private static final UserStorage instance = new UserStorage();
    private final PetStorage pets = PetStorage.getInstance();

    private UserStorage() {
    }

    public static UserStorage getInstance() {
        return instance;
    }

    public User add(User user) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into users (username, fullname, password, enabled, phone, email, role_id) values (?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFullname());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isEnabled());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setInt(7, user.getRole().getId());
            statement.executeUpdate();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in creating user", e);
        }
        return user;
    }

    public void update(User user) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "update users set username=?, fullname=?, password=?, enabled=?, phone=?, email=?, role_id=? where id=?"
             )) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFullname());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isEnabled());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setInt(7, user.getRole().getId());
            statement.setInt(8, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred in creating user", e);
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     Joiner.on("").join(
                             "select u.id as uid, u.username as uusername, ",
                             "u.fullname as ufullname, u.password as upassword, ",
                             "u.enabled as uenabled, u.phone as uphone, ",
                             "u.email as uemail, r.id as rid, r.name as rname ",
                             "from users as u, roles as r ",
                             "where u.role_id=r.id"
                     )
             )) {
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("uid"));
                    user.setUsername(rs.getString("uusername"));
                    user.setFullname(rs.getString("ufullname"));
                    user.setPassword(rs.getString("upassword"));
                    user.setEnabled(rs.getBoolean("uenabled"));
                    user.setPhone(rs.getString("uphone"));
                    user.setEmail(rs.getString("uemail"));
                    Role role = new Role();
                    role.setId(rs.getInt("rid"));
                    role.setName(rs.getString("rname"));
                    user.setRole(role);
                    user.setPets(this.pets.findByOwnerId(user.getId()));
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }

    public Optional<User> findByCridentional(String username, String password) {
        Optional<User> result = Optional.empty();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     Joiner.on("").join(
                             "select u.id as uid, u.username as uusername, ",
                             "u.fullname as ufullname, u.password as upassword, ",
                             "u.enabled as uenabled, u.phone as uphone, ",
                             "u.email as uemail, r.id as rid, r.name as rname ",
                             "from users as u, roles as r ",
                             "where u.username=? and u.password=? and u.role_id=r.id"
                     )
             )) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("uid"));
                    user.setUsername(rs.getString("uusername"));
                    user.setFullname(rs.getString("ufullname"));
                    user.setPassword(rs.getString("upassword"));
                    user.setEnabled(rs.getBoolean("uenabled"));
                    user.setPhone(rs.getString("uphone"));
                    user.setEmail(rs.getString("uemail"));
                    Role role = new Role();
                    role.setId(rs.getInt("rid"));
                    role.setName(rs.getString("rname"));
                    user.setRole(role);
                    user.setPets(this.pets.findByOwnerId(user.getId()));
                    result = Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }

    public Optional<User> findById(final int id) {
        Optional<User> result = Optional.empty();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     Joiner.on("").join(
                             "select u.id as uid, u.username as uusername, ",
                             "u.fullname as ufullname, u.password as upassword, ",
                             "u.enabled as uenabled, u.phone as uphone, ",
                             "u.email as uemail, r.id as rid, r.name as rname ",
                             "from users as u, roles as r ",
                             "where u.id=(?) and u.role_id=r.id"
                     )
             )) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("uid"));
                    user.setUsername(rs.getString("uusername"));
                    user.setFullname(rs.getString("ufullname"));
                    user.setPassword(rs.getString("upassword"));
                    user.setEnabled(rs.getBoolean("uenabled"));
                    user.setPhone(rs.getString("uphone"));
                    user.setEmail(rs.getString("uemail"));
                    Role role = new Role();
                    role.setId(rs.getInt("rid"));
                    role.setName(rs.getString("rname"));
                    user.setRole(role);
                    user.setPets(this.pets.findByOwnerId(user.getId()));
                    result = Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }

    public void delete(int id) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "delete from users where id=?",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
    }
}
