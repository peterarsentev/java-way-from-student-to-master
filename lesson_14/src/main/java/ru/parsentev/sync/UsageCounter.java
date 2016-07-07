package ru.parsentev.sync;

/**
 * TODO: comment
 * @author parsentev
 * @since 08.05.2016
 */
public class UsageCounter implements Runnable {
	private final Count count;

	public UsageCounter(final Count count) {
		this.count = count;
	}

	@Override
	public synchronized void run() {
		System.out.println(this.count.increment());
	}

	public static void main(String[] args) {
		//share object
		Count count = new Count();
		//thread 1
		new Thread(new UsageCounter(count)).start();
		//thread 2
		new Thread(new UsageCounter(count)).start();
	}
}