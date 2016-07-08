package ru.parsentev.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 08.07.2016
 */
public class UserEqualsTest {
    @Test
    public void reflexive() {
        User first = new User(1, "Petr");
        assertEquals(first, first);
    }

    @Test
    public void symmetric() {
        User first = new User(1, "Petr");
        User second = new User(1, "Petr");
        assertTrue(first.equals(second) && second.equals(first));
    }

    @Test
    public void transitive() {
        User first = new User(1, "Petr");
        User second = new User(1, "Petr");
        User third = new User(1, "Petr");
        assertTrue(first.equals(second) && second.equals(third) && third.equals(first));
    }

    @Test
    public void consistent() {
        User first = new User(1, "Petr");
        User second = new User(1, "Petr");
        assertEquals(first, second);
        assertEquals(first, second);
    }

    @Test
    public void nullable() {
        User first = new User(1, "Petr");
        assertFalse(first.equals(null));
    }
}