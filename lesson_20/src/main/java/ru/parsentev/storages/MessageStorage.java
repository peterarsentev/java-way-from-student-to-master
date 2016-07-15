package ru.parsentev.storages;

import com.google.common.base.Joiner;
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

    private MessageStorage() {
    }

    public static MessageStorage getInstance() {
        return instance;
    }

    public Message add(Message message) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into messages (text, created, owner_id, author_id) values (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, message.getText());
            statement.setTimestamp(2, message.getCreated());
            statement.setInt(3, message.getOwner().getId());
            statement.setInt(4, message.getAuthor().getId());
            statement.executeUpdate();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    message.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
        return message;
    }

    public List<Message> findByOwner(final int id) {
        List<Message> result = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     Joiner.on("").join(
                             "select m.id as mid, m.text as mtext, m.created as mcreated, ",
                             "u.id as uid, u.username as uusername, ",
                             "u.fullname as ufullname, u.password as upassword, ",
                             "u.enabled as uenabled, u.phone as uphone, ",
                             "u.email as uemail ",
                             "from messages as m, users as u where m.owner_id=? and m.owner_id=u.id"
                     )
             )) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setId(rs.getInt("mid"));
                    message.setText(rs.getString("mtext"));
                    message.setCreated(rs.getTimestamp("mcreated"));
                    User author = new User();
                    author.setId(rs.getInt("uid"));
                    author.setUsername(rs.getString("uusername"));
                    author.setFullname(rs.getString("ufullname"));
                    author.setPassword(rs.getString("upassword"));
                    author.setEnabled(rs.getBoolean("uenabled"));
                    author.setPhone(rs.getString("uphone"));
                    author.setEmail(rs.getString("uemail"));
                    message.setAuthor(author);
                    result.add(message);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }


    public void deleteByOwner(int id) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "delete from messages where owner_id=?",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
    }

    public void delete(int messageId) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "delete from messages where id=?",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setInt(1, messageId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
    }
}
