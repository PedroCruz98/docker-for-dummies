package com.example.events4all.events.requests.list;

import com.example.events4all.events.services.EventManager;
import com.example.events4all.utils.requests.ReqResController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event-management")
public class ListEventsByResponsibleController
        implements ReqResController<String, ListEventsResponse> {

    private final EventManager eventManager;

    public ListEventsByResponsibleController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    @GetMapping(path = "/list-by-responsible/{responsible}")
    public ResponseEntity<ListEventsResponse> call(@PathVariable("responsible") String responsible) {
        ListEventsResponse response = eventManager.listEventsByResponsible(responsible);
        return ResponseEntity.ok(response);
    }
}
