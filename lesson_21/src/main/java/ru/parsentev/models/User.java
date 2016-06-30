package ru.parsentev.models;

import java.util.Calendar;

/**
 *
 * @author parsentev
 * @since 28.06.2016
 */
public class User {
    private int id;
    private String name;
    private Calendar created;

    public User() {
    }

    public User(String name, Calendar created) {
        this();
        this.name = name;
        this.created = created;
    }

    public User(int id) {
        this();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
