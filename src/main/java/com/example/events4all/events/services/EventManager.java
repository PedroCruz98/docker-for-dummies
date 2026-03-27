package com.example.events4all.events.services;

import com.example.events4all.events.Event;
import com.example.events4all.events.persistence.EventRepository;
import com.example.events4all.events.requests.create.CreateEventRequest;
import com.example.events4all.events.requests.create.CreateEventResponse;
import com.example.events4all.events.requests.list.ListEventsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EventManager {

    private final EventRepository repository;

    public EventManager(EventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CreateEventResponse createEvent(CreateEventRequest request) {
        String id = UUID.randomUUID().toString();
        Event event = new Event(
                id,
                request.eventName(),
                request.start(),
                request.end(),
                request.responsible(),
                request.local()
        );
        repository.createNewEvent(event);
        return new CreateEventResponse(
                event.uuid(),
                event.eventName(),
                event.startTime(),
                event.endTime(),
                event.responsible(),
                event.local()
        );
    }

    public ListEventsResponse listEventsByResponsible(String responsible) {
        List<Event> events = repository.getEventsByResponsible(responsible);
        return new ListEventsResponse(events);
    }

    public ListEventsResponse listAllEvents() {
        List<Event> events = repository.getAllEvents();
        return new ListEventsResponse(events);
    }
}
