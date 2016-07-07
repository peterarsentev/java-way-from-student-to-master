package ru.parsentev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.User;

import java.util.List;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findByFullnameLike(String fullname);
}
