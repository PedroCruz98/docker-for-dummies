package com.example.events4all.events.requests.create;

import com.example.events4all.utils.requests.Request;

import java.time.LocalDateTime;

public record CreateEventRequest(
        String eventName,
        LocalDateTime start,
        LocalDateTime end,
        String responsible,
        String local
) implements Request {
    public CreateEventRequest {
        if (eventName == null || eventName.isBlank()) {
            throw new IllegalArgumentException("Event needs a name");
        }
        if (responsible == null || responsible.isBlank()) {
            throw new IllegalArgumentException("No responsible provided");
        }
        if (local == null || local.isBlank()) {
            throw new IllegalArgumentException("Local needs to be defined");
        }
        if (start == null) {
            throw new IllegalArgumentException("We need a startTime date");
        }
        if (end == null) {
            throw new IllegalArgumentException("We need and end date");
        }
    }
}
