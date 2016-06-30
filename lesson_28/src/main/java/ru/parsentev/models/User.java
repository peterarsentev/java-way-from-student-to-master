package ru.parsentev.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 22.06.2016
 */
public class User {
    private static final Logger log = LoggerFactory.getLogger(User.class);
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
