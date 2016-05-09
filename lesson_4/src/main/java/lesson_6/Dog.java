package lesson_6;

/**
 * Класс описывает песа.
 * @author parsentev
 * @since 16.04.2015
 */
public class Dog extends Animal {

	public Dog(String name) {
		super(name);
	}

	/**
	 * Поймать кота.
	 */
	public void catchCat() {
	}

	/**
	 * Голодный пес или нет
	 * @return true - если голодный.
	 */
	private boolean isHungry() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return String.format("Dog says %s.", super.getName());
	}
}
