package ru.parsentev.models;

import java.util.Date;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 07.07.2016
 */
public class MessageJson {
    private int id;
    private String text;
    private Date created;
    private String username;
    private String fullname;

    public MessageJson(int id, String text, Date created, String username, String fullname) {
        this.id = id;
        this.text = text;
        this.created = created;
        this.username = username;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreated() {
        return created;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }
}
