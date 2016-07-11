package ru.parsentev.models;

import java.util.Calendar;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 10.07.2016
 */
public class User {
    private int id;
    private String name;
    private int age;
    private Calendar birthday;
    private boolean active;

    public User(int id, String name, int age, Calendar birthday, boolean active) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.active = active;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
