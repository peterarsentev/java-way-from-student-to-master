package ru.parsentev.engineer;

/**
 * TODO: comment
 * @author parsentev
 * @since 20.08.2015
 */
public class Option<T> {
	public static final Option EMPTY = new Option(null);

	private final T value;

	public Option(T value) {
		this.value = value;
	}

	public boolean isDefiny() {
		return this.value != null;
	}

	public boolean isEmpty() {
		return this.value == null;
	}

	public T get() {
		return this.value;
	}

	T getOrElse(T other) {
		return this.isDefiny() ? this.get() : other;
	}

	public <B> B getOrElse(Treat<T, B> treat, T other) {
		return treat.action(this.getOrElse(other));
	}

	interface Treat<T, B> {
		B action(T t);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Option<?> option = (Option<?>) o;

		if (value != null ? !value.equals(option.value) : option.value != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}
}