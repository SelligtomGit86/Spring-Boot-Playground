package com.example.demotest.mapper;

import com.example.demotest.model.Event;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();

        event.setId(rs.getInt("id"));
        event.setEventName(rs.getString("eventname"));
        event.setEventDesc(rs.getString("eventdesc"));
        event.setEventType(rs.getString("eventtype"));
        event.setTs(rs.getTimestamp("ts"));

        return event;
    }

}
