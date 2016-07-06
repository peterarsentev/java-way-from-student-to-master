package ru.parsentev.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.User;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
