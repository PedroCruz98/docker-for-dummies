package com.example.events4all.events;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("EVENTS")
public record Event(
        @Id String uuid,
        String eventName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String responsible,
        String local
) {
}
