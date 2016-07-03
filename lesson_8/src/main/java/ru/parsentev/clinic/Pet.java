package ru.parsentev.clinic;

/**
 * Интерфейс описывает животное.
 * @author parsentev
 * @since 16.04.2015
 */
public interface Pet {
	/**
	 * Имя животного.
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
	 * ID.
	 * @param id
	 */
	void setId(int id);
}