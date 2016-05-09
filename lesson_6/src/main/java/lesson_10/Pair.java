package lesson_10;

/**
 * TODO: comment
 * @author parsentev
 * @since 22.08.2015
 */
public class Pair<T, K> {
	public final T key;
	public final K value;

	public Pair(T key, K value) {
		this.key = key;
		this.value = value;
	}
}
