package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 21.06.2016
 */
public interface UserDataRepository extends CrudRepository<User, Integer> {
}
