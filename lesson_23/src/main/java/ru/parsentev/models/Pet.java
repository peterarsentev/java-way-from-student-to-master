package ru.parsentev.models;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 05.07.2016
 */
public class Pet {
    private int id;
    private String nick;
    private PetType type;
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (id != pet.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
