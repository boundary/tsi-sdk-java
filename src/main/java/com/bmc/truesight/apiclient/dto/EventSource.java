/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * Defined the source for the event.
 */
public final class EventSource implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private final String type;
    private final String ref;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> name;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Map<String, Object>> properties;

    /**
     * Initializing only the required parameters.
     * Optional fields should be set using the respective setter methods.
     * @param type The type of event source (e.g. 'host', 'conversation', 'organization').
     * @param ref The reference / identifier for the event source (e.g. ip address, name, database id, meter observation domain id).
     */
    public EventSource(String type, String ref) {
        this.type = type;
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public String getRef() {
        return ref;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<Map<String, Object>> getProperties() {
        return properties;
    }

    /** 
     * @param name An optional descriptive name for the event source.
     */
    public void setName(String name) {
        this.name = Optional.of(name);
    }

    /**
     * @param properties Used to store additional properties about the event source.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = Optional.of(properties);
    }
}
