package nl.fontys.esde;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Calendar {
    private String name;
    private final Set<Event> events = Collections.synchronizedSet(new LinkedHashSet<>());

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }
}
