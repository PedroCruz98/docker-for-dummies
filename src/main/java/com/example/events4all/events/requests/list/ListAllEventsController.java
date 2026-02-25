package com.example.events4all.events.requests.list;

import com.example.events4all.events.services.EventManager;
import com.example.events4all.utils.requests.ResponseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event-management")
public class ListAllEventsController
        implements ResponseController<String, ListEventsResponse> {

    private final EventManager eventManager;

    public ListAllEventsController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @GetMapping(path = "/list-all")
    public ResponseEntity<ListEventsResponse> call() {
        ListEventsResponse response = eventManager.listAllEvents();
        return ResponseEntity.ok(response);
    }
}
