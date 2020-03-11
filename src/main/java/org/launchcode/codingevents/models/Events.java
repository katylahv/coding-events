package org.launchcode.codingevents.models;

import java.util.Objects;

public class Events {
    private String name;
    private String description;
    private int id;
    private static int nextId = 1;

    public Events(String name, String description) {
        this.name = name;
        this.description = description;
        this.id  = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Events)) return false;
        Events events = (Events) o;
        return id == events.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
