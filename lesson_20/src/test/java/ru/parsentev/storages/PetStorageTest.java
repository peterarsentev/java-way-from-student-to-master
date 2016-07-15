package ru.parsentev.storages;

import org.junit.Test;
import ru.parsentev.models.Pet;
import ru.parsentev.models.PetType;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 15.07.2016
 */
public class PetStorageTest extends DbInit {
    final RoleStorage roles = RoleStorage.getInstance();
    final UserStorage users = UserStorage.getInstance();
    final PetStorage pets = PetStorage.getInstance();
    final PetTypeStorage types = PetTypeStorage.getInstance();

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setRole(this.roles.add(new Role()));
        user = this.users.add(user);
        Pet pet = new Pet();
        pet.setOwner(user);
        pet.setType(this.types.add(new PetType()));
        this.pets.add(pet);
        assertThat(pet, is(this.pets.findById(pet.getId()).get()));
    }
}