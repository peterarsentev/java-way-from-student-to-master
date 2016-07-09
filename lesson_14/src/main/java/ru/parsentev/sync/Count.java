package ru.parsentev.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author parsentev
 * @since 08.05.2016
 */
public class Count {
	private int value;

	public int increment() {
		synchronized (this) {
			return ++this.value;
		}
	}
}
