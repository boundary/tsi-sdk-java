/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Class that defines the Event object. Used in TrueSightAPIClient.createEvents() method.
 * @see EventSource
 */
public final class Event implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private final String title;
    private final List<String> fingerprintFields;    
    private final EventSource source;    

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> severity;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<EventSource> sender;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Map<String, Object>> properties;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> status;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<List<String>> tags;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> message;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Long> createdAt;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> eventClass;
    
    /**
     * Only mandatory fields are initialized. Optional fields need to be set using the corresponding setter methods.
     * @param title Description of the event.
     * @param fingerprintFields The fields of the event used to calculate the event fingerprint.
     * @param source The source of the event. The source is typically the hostname or ip address of the system this event refers to.
     */
    
    public Event(String title, List<String> fingerprintFields, EventSource source) {
        this.title = title;
        this.fingerprintFields = fingerprintFields;
        this.source = source;
    }
    
    public String getTitle() {
        return title;
    }   

    public List<String> getFingerprintFields() {
        return fingerprintFields;
    }

    public EventSource getSource() {
        return source;
    }

    public Optional<String> getSeverity() {
        return severity;
    }

    public Optional<EventSource> getSender() {
        return sender;
    }

    public Optional<Map<String, Object>> getProperties() {
        return properties;
    }

    public Optional<String> getStatus() {
        return status;
    }

    public Optional<List<String>> getTags() {
        return tags;
    }

    public Optional<String> getMessage() {
        return message;
    }

    public Optional<Long> getCreatedAt() {
        return createdAt;
    }

    public Optional<String> getEventClass() {
        return eventClass;
    }
    
    /**
     * Method used to set the optional field severity.
     * @param severity Optional free-form text. Good default choices are one of INFO, WARN, ERROR, CRITICAL. Default is an empty string.
     */
    public void setSeverity(String severity) {
        this.severity = Optional.of(severity);
    }

    /**
     * Method used to set the optional field sender.
     * @param sender Optional information about the sender of the event. This is used to describe a third party event system forwarding this event into TrueSight, or a TrueSight service sending the event.
     */
    public void setSender(EventSource sender) {
        this.sender = Optional.of(sender);
    }

    /**
     * Method used to set the optional field properties.
     * @param properties Properties for the event.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = Optional.of(properties);
    }

    /**
     * Method used to set the optional field status.
     * @param status Optional mostly free-form text. The value CLOSED is used to indicate a closed event. As example, good choices may be one of OPEN, CLOSED, ACKNOWLEDGED, or OK.
     */
    public void setStatus(String status) {
        this.status = Optional.of(status);
    }

    /**
     * Method used to set the optional field tags.
     * @param tags Tags used to provide a classification for events.
     */
    public void setTags(List<String> tags) {
        this.tags = Optional.of(tags);
    }

    /**
     * Method used to set the optional field message.
     * @param message Additional description of the event.
     */
    public void setMessage(String message) {
        this.message = Optional.of(message);
    }

    /**
     * Method used to set the optional field createdAt.
     * @param createdAt The timestamp the event was created. If not specified, this is set to the time the event is received.
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = Optional.of(createdAt);
    }

    /**
     * Method used to set the optional field eventCalss.
     * @param eventClass Type of the event. Used by TrueSight to categorize the event. If not supplied, defaults to UNKNOWN.
     */
    public void setEventClass(String eventClass) {
        this.eventClass = Optional.of(eventClass);
    }
}