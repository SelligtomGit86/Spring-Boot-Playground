package com.example.demotest.model;

import java.sql.Timestamp;

public class Event {
    private int id;
    private String eventType;
    private String eventName;
    private String eventDesc;
    private Timestamp ts;

    public Event() {
    }

    public Event(int id, String eventType, String eventName, String eventDesc, Timestamp ts) {
        this.id = id;
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.ts = ts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDesc='" + eventDesc + '\'' +
                ", ts=" + ts +
                '}';
    }
}
