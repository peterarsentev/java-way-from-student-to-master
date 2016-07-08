package ru.parsentev.models;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.07.2016
 */
public class UserTest {
    private static final class User {
        private String name;

        public User(String name) {
            this.name = name;
        }
    }

    private static final class UserHashCode {
        private String name;

        public UserHashCode(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    private static final class UserHashCodeEquals {
        private String name;

        public UserHashCodeEquals(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserHashCodeEquals that = (UserHashCodeEquals) o;

            if (name != null ? !name.equals(that.name) : that.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    @Test
    public void addWithoutHashCodeEquals() {
        HashSet<User> users = new HashSet<>();
        users.add(new User("Petr"));
        users.add(new User("Petr"));
        assertThat(users.size(), is(2));
    }

    @Test
    public void addWithoutHashCode() {
        HashSet<UserHashCode> users = new HashSet<>();
        users.add(new UserHashCode("Petr"));
        users.add(new UserHashCode("Petr"));
        assertThat(users.size(), is(2));
    }

    @Test
    public void add() {
        HashSet<UserHashCodeEquals> users = new HashSet<>();
        users.add(new UserHashCodeEquals("Petr"));
        users.add(new UserHashCodeEquals("Petr"));
        assertThat(users.size(), is(1));
    }
}
