package ru.parsentev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.PetType;
import ru.parsentev.models.User;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {
}
