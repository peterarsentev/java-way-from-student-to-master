package lesson_10;

import lesson_6.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment
 * @author parsentev
 * @since 28.08.2015
 */
public class StaticAnalize {
	public static void main(String[] arg) {
		List list = new ArrayList();

		list.add(new Dog("Dog"));
		list.add(new Object());
	}
}
