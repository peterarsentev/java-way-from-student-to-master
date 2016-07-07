package ru.parsentev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.Role;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
