package com.example.events4all.events.requests.list;

import com.example.events4all.events.Event;
import com.example.events4all.utils.requests.Response;

import java.util.List;

public record ListEventsResponse(List<Event> events)
        implements Response<String> {
}
