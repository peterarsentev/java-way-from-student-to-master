package ru.parsentev.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 22.06.2016
 */
public class Item {
    private static final Logger log = LoggerFactory.getLogger(Item.class);
    private int id;
    private String name;
    private String desc;
    private User handler;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getHandler() {
        return handler;
    }

    public void setHandler(User handler) {
        this.handler = handler;
    }
}
