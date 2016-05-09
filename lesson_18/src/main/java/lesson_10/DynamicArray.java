package lesson_10;

import java.util.Arrays;
import java.util.Iterator;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class DynamicArray<T> implements Iterable<T> {
	private int size = 0;
	private Object[] array;

	public int size() {
		return this.size;
	}

	public DynamicArray(int capacity) {
		this.array = new Object[capacity];
	}

	public void add(T t) {
		++size;
		if (this.size >= array.length) {
			array = Arrays.copyOf(this.array, size * 2);
		}
		this.array[size-1] = t;
	}

	public T get(int i) {
		return (T) this.array[i];
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int pos = 0;
			@Override
			public boolean hasNext() {
				return size > pos;
			}

			@Override
			public T next() {
				return (T) array[pos++];
			}
		};
	}
}
