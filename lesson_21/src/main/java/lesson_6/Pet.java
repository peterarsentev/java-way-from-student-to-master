package lesson_6;

/**
 * Интерфейс описывает животное.
 * @author parsentev
 * @since 16.04.2015
 */
public interface Pet {
	/**
	 * Установить имя.
	 * @param name
	 * @return
	 */
	void setName(String name);

	/**
	 * ID.
	 * @return
	 */
	int getId();

	/**
	 * Имя животного.
	 * @return
	 */
	String getName();

	/**
	 * Установить ID.
	 * @param id
	 */
	void setId(int id);
}