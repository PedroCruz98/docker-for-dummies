package com.example.events4all.events.persistence;

import com.example.events4all.events.Event;

import java.util.List;

public interface EventRepository {

    Event createNewEvent(Event event);

    List<Event> getEventsByResponsible(String responsible);

    List<Event> getAllEvents();
}
