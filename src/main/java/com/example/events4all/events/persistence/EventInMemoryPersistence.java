package com.example.events4all.events.persistence;

import com.example.events4all.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Profile("memory | !oracle")
public class EventInMemoryPersistence implements EventRepository {
    private static final Logger log = LoggerFactory.getLogger(EventInMemoryPersistence.class);
    private final List<Event> events;

    public EventInMemoryPersistence() {
        this.events = new ArrayList<>();
    }

    @Override
    public Event createNewEvent(Event event) {
        if (event == null) {
            log.warn("Attempted to persist a null event");
            return null;
        }
        if (!events.contains(event)) {
            log.warn("New event added: {}", event);
            this.events.add(event);
        }
        return events.stream()
                .filter(e -> e.uuid().equals(event.uuid()))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
    }

    @Override
    public List<Event> getEventsByResponsible(String responsible) {
        return events.stream()
                .filter(e -> e.responsible().equals(responsible))
                .toList();
    }

    @Override
    public List<Event> getAllEvents() {
        return Collections.unmodifiableList(events);
    }
}
