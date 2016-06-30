package ru.parsentev.models;

import org.slf4j.Logger;

import java.util.Calendar;

import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author parsentev
 * @since 27.06.2016
 */
public class User {
    private static final Logger log = getLogger(User.class);
    private String name;
    private String login;
    private Calendar created;

    public User(String name, String login, Calendar created) {
        this.name = name;
        this.login = login;
        this.created = created;
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

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
