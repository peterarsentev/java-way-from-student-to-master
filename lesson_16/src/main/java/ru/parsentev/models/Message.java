package ru.parsentev.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 05.07.2016
 */
public class Message {
    private int id;
    private String text;
    private Timestamp created;
    private User owner;
    private User author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
