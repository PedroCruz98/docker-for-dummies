package com.example.events4all.events.requests.create;

import com.example.events4all.utils.requests.Response;

import java.time.LocalDateTime;

public record CreateEventResponse(
        String uuid,
        String eventName,
        LocalDateTime start,
        LocalDateTime end,
        String responsible,
        String local
) implements Response<CreateEventRequest> {
}
