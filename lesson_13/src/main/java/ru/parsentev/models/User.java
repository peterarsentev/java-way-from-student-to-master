package ru.parsentev.models;

import java.util.Arrays;
import java.util.Calendar;

/**
 * TODO: comment
 * @author parsentev
 * @since 09.05.2016
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private float heigh;
    private String[] children;
    private Calendar created;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getHeigh() {
        return heigh;
    }

    public void setHeigh(float heigh) {
        this.heigh = heigh;
    }

    public String[] getChildren() {
        return children;
    }

    public void setChildren(String[] children) {
        this.children = children;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", heigh=" + heigh +
                ", children=" + Arrays.toString(children) +
                ", created=" + created +
                '}';
    }
}
