package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author parsentev
 * @since 21.06.2016
 */
public interface Repository<T> {
    /**
     * Save modul.
     * @param model
     * @return
     */
    @Transactional
    T save(T model);

    /**
     * Get all models.
     * @return list models.
     */
    List<T> getAll();
}