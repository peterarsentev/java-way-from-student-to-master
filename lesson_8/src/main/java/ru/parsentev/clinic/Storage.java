package ru.parsentev.clinic;

import java.util.Collection;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public interface Storage<T, K> {
	K put(T key, K value);

	K get(T key);

	K remove(T key);

	Collection<K> values();
}
