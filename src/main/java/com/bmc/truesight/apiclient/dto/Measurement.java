/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.dto;

import java.io.Serializable;
import java.util.Optional;

/**
 * Object that defines a Metric which is used in addMeasures() method.
 */
public final class Measurement implements Serializable
{
   
    private static final long serialVersionUID = 1L;
        
    private final String source;
    private final String metric;
    private final double measure;
    private final long timestamp;
    private final String appID;
        
    private Optional<String> origin;
 
    /**
     * Initializes only the mandatory fields.
     * @param source The source of the metric
     * @param metric The name of the metric that you've set up in your account
     * @param measure Numeric measure to report
     * @param timestamp Unix timestamp the measurement was taken. If omitted, uses the time at which the measure is received by TrueSight Pulse.
     * @param appID Application ID/Name. This is a tag  and results in associating the measurement with an application object in TrueSight Intelligence. For example, to associate a metric with an application named EngageCash, specify app_id=EngageCash as a property.
     */
    public Measurement(String source, String metric, double measure, long timestamp, String appID)
    {
        this.source = source;
        this.metric = metric;
        this.measure = measure;
        this.timestamp = timestamp;
        this.appID =appID;  
        //Creating a empty optional object.
        //Else the ifPresent will throw nullpointer when the value is not set.
        this.origin = Optional.empty();
    }   

    public String getSource() {
        return source;
    }

    public String getMetric() {
        return metric;
    }

    public double getMeasure() {
        return measure;
    }
    
    public long getTimestamp(){
        return timestamp;
    }

    public String getAppID() {
        return appID;
    }

    public Optional<String> getOrigin() {
        return origin;
    }    

    /**
     * Sets the optional field Origin.
     * @param origin Host/server for which the data is being collected by a plugin. This is a tag which forces TrueSight Intelligence to create a Device object. For example, when a plugin is monitoring an application running on host myoracle.bmc.com, a property of origin=myoracle.bmc.com can be specified.
     */
    public void setOrigin(String origin) {
        this.origin = Optional.of(origin);        
    }
            
}