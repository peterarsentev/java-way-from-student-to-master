package ru.parsentev.collections;

import ru.parsentev.clinic.Storage;

import java.util.Collection;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class LinkArrayStorage<T, K> implements Storage<T, K> {
	private LinkArray<Pair<T, K>> array = new LinkArray<>();

	@Override
	public K put(T key, K value) {
		this.array.add(new Pair<T, K>(key, value));
		return value;
	}

	@Override
	public K get(T key) {
		return null;
	}

	@Override
	public K remove(T key) {
		return null;
	}

	@Override
	public Collection<K> values() {
		return null;
	}
}
