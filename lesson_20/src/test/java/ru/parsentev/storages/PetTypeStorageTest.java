package ru.parsentev.storages;

import org.junit.Test;
import ru.parsentev.models.PetType;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 14.07.2016
 */
public class PetTypeStorageTest extends DbInit {
    final PetTypeStorage types = PetTypeStorage.getInstance();

    @Test
    public void testAdd() throws Exception {
        PetType type = this.types.add(new PetType());
        assertThat(type, is(this.types.findById(type.getId()).get()));
    }

    @Test
    public void testGetAll() throws Exception {
        PetType type = this.types.add(new PetType());
        assertTrue(this.types.getAll().contains(type));
    }
}