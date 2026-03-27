package com.example.events4all.events;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("EVENTS")
public record Event(
        @Id
        @Column("UUID") String uuid,
        @Column("EVENT_NAME") String eventName,
        @Column("START_TIME") LocalDateTime startTime,
        @Column("END_TIME") LocalDateTime endTime,
        @Column("RESPONSIBLE") String responsible,
        @Column("LOCAL") String local
) implements Persistable<String> {

        @Override
        public String getId() {
                return uuid;
        }

        @Override
        @Transient
        public boolean isNew() {
                return true;
        }
}
