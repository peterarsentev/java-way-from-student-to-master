package ru.parsentev.storages;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.Pet;
import ru.parsentev.models.PetType;
import ru.parsentev.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 11.07.2016
 */
public class PetStorage {
    private static final Logger log = LoggerFactory.getLogger(PetStorage.class);
    private static final PetStorage instance = new PetStorage();

    private PetStorage() {
    }

    public static PetStorage getInstance() {
        return instance;
    }

    public Pet add(Pet pet) {
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     "insert into pets (nick, type_id, user_id) values (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setString(1, pet.getNick());
            statement.setInt(2, pet.getType().getId());
            statement.setInt(3, pet.getOwner().getId());
            statement.executeUpdate();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    pet.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in creating role", e);
        }
        return pet;
    }

    public Optional<Pet> findById(final int id) {
        Optional<Pet> result = Optional.empty();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     Joiner.on("").join(
                             "select p.id as pid, p.nick as pnick, t.id as tid, t.name as tname ",
                             "from pets as p, pet_types as t where p.id=? and p.type_id=t.id"
                     )
             )) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet pet = new Pet();
                    pet.setId(rs.getInt("pid"));
                    pet.setNick(rs.getString("pnick"));
                    PetType type = new PetType();
                    type.setId(rs.getInt("tid"));
                    type.setName(rs.getString("tname"));
                    pet.setType(type);
                    result = Optional.of(pet);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return result;
    }

    public List<Pet> findByOwnerId(final int id) {
        final List<Pet> pets = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement(
                     Joiner.on("").join(
                             "select p.id as pid, p.nick as pnick, t.id as tid, t.name as tname ",
                             "from pets as p, pet_types as t where p.user_id=? and p.type_id=t.id"
                     )
             )) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet pet = new Pet();
                    pet.setId(rs.getInt("pid"));
                    pet.setNick(rs.getString("pnick"));
                    PetType type = new PetType();
                    type.setId(rs.getInt("tid"));
                    type.setName(rs.getString("tname"));
                    pet.setType(type);
                    pets.add(pet);
                }
            }
        } catch (SQLException e) {
            log.error("Error occurred in getting user", e);
        }
        return pets;
    }

    public List<Pet> getAll() {
        final List<Pet> types = new ArrayList<>();
        try (final Connection connection = Pool.getDataSource().getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet rs = statement.executeQuery(
                     Joiner.on("").join(
                             "select p.id as pid, p.nick as pnick, t.id as tid, t.name as tname",
                             "from pets as p, pet_types as t where p.type_id=t.id"
                     )
             )) {
            while (rs.next()) {
                Pet pet = new Pet();
                pet.setId(rs.getInt("pid"));
                pet.setNick(rs.getString("pnick"));
                PetType type = new PetType();
                type.setId(rs.getInt("tid"));
                type.setName(rs.getString("tname"));
                pet.setType(type);
                types.add(pet);
            }
        } catch (final SQLException e) {
            log.error("Error occurred in getting list of users", e);
        }
        return types;
    }
}
