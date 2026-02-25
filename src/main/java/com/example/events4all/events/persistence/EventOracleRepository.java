package com.example.events4all.events.persistence;

import com.example.events4all.events.Event;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.StreamSupport;

@Profile("oracle")
public interface EventOracleRepository extends CrudRepository<Event, String>, EventRepository {

    List<Event> findByResponsible(String responsible);

    @Query("SELECT * FROM EVENTS WHERE responsible = :responsible ORDER BY start_time")
    List<Event> findEventsByResponsible(@Param("responsible") String responsible);

    @Override
    default List<Event> getAllEvents() {
        return StreamSupport.stream(findAll().spliterator(), false).toList();
    }
}
