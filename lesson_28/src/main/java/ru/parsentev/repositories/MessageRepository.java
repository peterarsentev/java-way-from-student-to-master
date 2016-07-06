package ru.parsentev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.Message;
import ru.parsentev.models.User;
import java.util.List;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByOwner(User owner);

    long deleteByOwner(User user);
}
