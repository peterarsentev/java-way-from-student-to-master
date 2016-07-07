package ru.parsentev.repositories;

import org.junit.Ignore;
import org.junit.Test;
import ru.parsentev.models.Pet;
import ru.parsentev.models.User;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO use memory rdbm data base
 * @author parsentev
 * @since 28.06.2016
 */
@Ignore
public class MappingTest {
    @Test
    public void whenAddPetShouldExitsInUser() {
        final UserRepository userRepository = new UserRepository();
        final PetRepository petRepository = new PetRepository();
        User user = userRepository.create(new User("Petr Arsentev", Calendar.getInstance()));
        Pet pet = petRepository.create(new Pet("Nick", new User(user.getId())));
        assertTrue(userRepository.findById(user.getId()).getPets().contains(pet));
    }
}