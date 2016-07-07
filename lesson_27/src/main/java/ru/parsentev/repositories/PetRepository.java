package ru.parsentev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.Pet;
import ru.parsentev.models.User;

import java.util.List;

/**
 * @author parsentev
 * @since 05.07.2016
 */
public interface PetRepository extends CrudRepository<Pet, Integer> {
    List<Pet> findByOwner(User user);

    long deleteByOwner(User user);
}
