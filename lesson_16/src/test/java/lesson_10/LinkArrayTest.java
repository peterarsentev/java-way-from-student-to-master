package lesson_10;

import lesson_6.Dog;
import lesson_6.Pet;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class LinkArrayTest {
	@Test
	public void testAdd() {
		final LinkArray<Pet> array = new LinkArray<>();
		Pet dog = new Dog("Sparky");
		array.add(dog);

		assertThat(array.iterator().next(), is(dog));
	}

	@Test
	public void testWhenIteratorExecuteItShouldLoopTheSameTimeAsSize() {
		final LinkArray<Pet> array = new LinkArray<>();
		array.add(new Dog("Sparky"));
		array.add(new Dog("Pepe"));

		int loop = 0;
		for (Pet pet : array) {
			++loop;
		}

		assertThat(loop, is(array.size()));
	}
}