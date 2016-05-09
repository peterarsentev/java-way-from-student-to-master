package lesson_6;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class MapStorage<T, K> implements Storage<T, K> {
	private final Map<T, K> clients = new LinkedHashMap<>();

	@Override
	public K put(T key, K value) {
		return this.clients.put(key, value);
	}

	@Override
	public K get(T key) {
		return this.clients.get(key);
	}

	@Override
	public K remove(T key) {
		return this.clients.remove(key);
	}

	@Override
	public Collection<K> values() {
		return this.clients.values();
	}
}
