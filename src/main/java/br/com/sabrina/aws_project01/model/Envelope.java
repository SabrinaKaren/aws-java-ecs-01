package br.com.sabrina.aws_project01.model;

import br.com.sabrina.aws_project01.enums.EventType;

public class Envelope {

    private EventType eventType;
    private String data;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}