package com.example.events4all.events.requests.create;

import com.example.events4all.events.services.EventManager;
import com.example.events4all.utils.requests.ReqResController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/event-management")
public class CreateEventController
        implements ReqResController<CreateEventRequest, CreateEventResponse> {

    private final EventManager manager;

    public CreateEventController(EventManager manager) {
        this.manager = manager;
    }

    @Override
    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<CreateEventResponse> call(@RequestBody CreateEventRequest request) {
        CreateEventResponse response = manager.createEvent(request);
        return ResponseEntity.ok(response);
    }
}
