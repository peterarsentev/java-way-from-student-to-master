package lesson_10;

import com.sun.xml.internal.bind.v2.model.core.Element;

import java.util.Iterator;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class LinkArray<T> implements Iterable<T> {
	Element<T> head;
	Element<T> current;
	private int size;

	public int size() {
		return this.size;
	}

	public void add(T t) {
		++size;
		if (this.head == null) {
			this.head = new Element<>();
			this.head.value = t;
		} else if(current == null) {
			this.current = new Element<>();
			this.current.back = head;
			this.current.value = t;
			this.head.next = this.current;
		} else {
			Element<T> next = new Element<>();
			next.value = t;
			next.back = this.current;
			this.current.next = next;
			this.current = next;
		}
	}

	public void remove(T t) {
		for (T value : this) {
			if (value.equals(t)) {

			}
		}
	}

	private static final class Element<T> {
		Element<T> back;
		Element<T> next;
		T value;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private lesson_10.LinkArray.Element<T> pos;

			@Override
			public boolean hasNext() {
				return pos == null || pos.next != null;
			}

			@Override
			public T next() {
				if (pos == null) {
					pos = head;
				} else {
					pos = pos.next;
				}
				return pos.value;
			}
		};
	}
}
