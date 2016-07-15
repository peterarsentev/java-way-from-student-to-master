package ru.parsentev.storages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.PetType;
import ru.parsentev.models.Role;

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
public class PetTypeStorage {
    private static final Logger log = LoggerFactory.getLogger(PetTypeStorage.class);
    private static final PetTypeStorage instance = new PetTypeStorage();

    private PetTypeStorage() {
    }

    public static PetTypeStorage getInstance() {
        return instance;
    }

    public PetType add(PetType type) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into pet_types (name) values (?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, type.getName());
            statement.executeUpdate();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    type.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
        return type;
    }

    public Optional<PetType> findById(int id) {
        Optional<PetType> result = Optional.empty();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("select * from pet_types where id=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PetType type = new PetType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    result = Optional.of(type);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }

    public List<PetType> getAll() {
        final List<PetType> types = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from pet_types")) {
            while (rs.next()) {
                PetType type = new PetType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
                types.add(type);
            }
        } catch (final SQLException e) {
            log.error("Error occurred in getting list of users", e);
        }
        return types;
    }
}
