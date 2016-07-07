package ru.parsentev.collections;

import org.junit.Test;
import ru.parsentev.clinic.Dog;
import ru.parsentev.clinic.Pet;
import ru.parsentev.collections.DynamicArray;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class DynamicArrayTest {

    @Test
    public void testWhenAddNewElArrayShouldIncrementSize() {
        final DynamicArray<Pet> array = new DynamicArray<>(1);
        array.add(new Dog("Sparky"));
        assertThat(array.size(), is(1));
    }

    @Test
    public void testWhenGetElByIndexItShouldReturnAppropriateValue() {
        final DynamicArray<Pet> array = new DynamicArray<>(1);
        final Pet dog = new Dog("Sparky");
        array.add(dog);
        assertThat(array.get(0), is(dog));
    }

    @Test
    public void testWhenIteratorExecuteItShouldLoopTheSameTimeAsSize() {
        final DynamicArray<Pet> array = new DynamicArray<>(2);
        array.add(new Dog("Sparky"));
        array.add(new Dog("Pepe"));

        int loop = 0;
        for (Pet pet : array) {
            ++loop;
        }

        assertThat(loop, is(array.size()));
    }
}