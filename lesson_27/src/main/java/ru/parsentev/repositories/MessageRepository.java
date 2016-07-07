package ru.parsentev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.Message;
import ru.parsentev.models.MessageJson;
import ru.parsentev.models.User;

import java.util.List;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Query("select new ru.parsentev.models.MessageJson(m.id, m.text, m.created, m.author.username, m.author.fullname) from ru.parsentev.models.Message m where m.owner = ?1")
    List<MessageJson> findJsonByOwner(User owner);

    List<Message> findByOwner(User owner);

    long deleteByOwner(User user);
}
