package com.example.demotest.repository;

import com.example.demotest.mapper.EventMapper;
import com.example.demotest.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;
    private final EventMapper eventMapper;

    @Autowired
    public EventRepository(
            JdbcTemplate jdbcTemplate,
            EventMapper eventMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventMapper = eventMapper;
    }

    public List<Event> findAll(){
        String query = "SELECT * FROM events";

        return  jdbcTemplate.query(query, eventMapper);
    }
    public Optional<Event> findById(int id){
        String query = "SELECT * FROM events WHERE id = ?";

        return  jdbcTemplate.query(query, eventMapper, id).stream().findFirst();
    }

    public int deleteById(int id) {
        String query = "DELETE FROM events WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }
}
