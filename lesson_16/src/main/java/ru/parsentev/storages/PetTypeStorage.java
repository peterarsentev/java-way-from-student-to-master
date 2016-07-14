package ru.parsentev.storages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.models.PetType;
import ru.parsentev.models.Role;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 11.07.2016
 */
public class PetTypeStorage {
    private static final Logger log = LoggerFactory.getLogger(PetTypeStorage.class);
    private static final PetTypeStorage instance = new PetTypeStorage();
    private List<PetType> types = new CopyOnWriteArrayList<>();
    private final AtomicInteger ids = new AtomicInteger(0);

    private PetTypeStorage() {
    }

    public static PetTypeStorage getInstance() {
        return instance;
    }

    public void add(PetType type) {
        type.setId(this.ids.incrementAndGet());
        this.types.add(type);
    }

    public Optional<PetType> findById(final int id) {
        return this.types.stream().filter(type -> type.getId() == id).findFirst();
    }

    public List<PetType> getAll() {
        return this.types;
    }
}
