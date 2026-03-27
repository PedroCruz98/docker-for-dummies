package com.example.events4all.events.persistence;

import com.example.events4all.events.Event;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Repository
@Profile("oracle")
public interface EventOracleRepository extends CrudRepository<Event, String>, EventRepository {

    @Override
    default Event createNewEvent(Event event) {
        createEvent(
                event.uuid(),
                event.eventName(),
                event.startTime(),
                event.endTime(),
                event.responsible(),
                event.local()
        );
        return event;
    }

    @Modifying
    @Query("INSERT INTO EVENTS (UUID, EVENT_NAME, START_TIME, END_TIME, RESPONSIBLE, LOCAL) " +
            "VALUES (:uuid, :eventName, :startTime, :endTime, :responsible, :local)")
    void createEvent(
            @Param("uuid") String uuid,
            @Param("eventName") String eventName,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("responsible") String responsible,
            @Param("local") String local
    );

    @Override
    @Query("SELECT * FROM EVENTS WHERE RESPONSIBLE = :responsible")
    List<Event> getEventsByResponsible(@Param("responsible") String responsible);

    @Override
    @Query("SELECT * FROM EVENTS")
    List<Event> getAllEvents();
}
