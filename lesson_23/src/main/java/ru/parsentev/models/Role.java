package ru.parsentev.models;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 05.07.2016
 */
public class Role {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
