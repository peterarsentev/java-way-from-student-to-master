package ru.parsentev.storages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RoleStorage {
    private static final Logger log = LoggerFactory.getLogger(RoleStorage.class);
    private static final RoleStorage instance = new RoleStorage();
    private List<Role> roles = new CopyOnWriteArrayList<>();
    private final AtomicInteger ids = new AtomicInteger(0);

    private RoleStorage() {
        Role admin = new Role();
        admin.setId(ids.incrementAndGet());
        admin.setName("ROLE_ADMIN");
        this.roles.add(admin);
    }

    public static RoleStorage getInstance() {
        return instance;
    }

    public void add(Role role) {
        role.setId(this.ids.incrementAndGet());
        this.roles.add(role);
    }

    public Optional<Role> findById(final int id) {
        return this.roles.stream().filter(role -> role.getId() == id).findFirst();
    }

    public List<Role> getAll() {
        return this.roles;
    }
}
