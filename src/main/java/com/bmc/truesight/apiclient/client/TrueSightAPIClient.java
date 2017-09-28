/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.client;

import com.bmc.truesight.apiclient.dto.Event;
import com.bmc.truesight.apiclient.dto.Measurement;
import com.bmc.truesight.apiclient.dto.Metric;
import com.bmc.truesight.apiclient.dto.TrueSightClientResponse;
import com.bmc.truesight.apiclient.dto.TrueSightCredentials;
import com.bmc.truesight.apiclient.exception.TrueSightClientException;
import com.bmc.truesight.apiclient.utility.Configuration;
import com.bmc.truesight.apiclient.utility.TrueSightAPIClientUtility;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jomathew 
 */
public final class TrueSightAPIClient { 
    
    private static final Logger LOG = LoggerFactory.getLogger(TrueSightAPIClient.class);
     
    /**
     * addMeasures() invoking the API /v1/measurements.
     * returns a TrueSightClientResponse on successful execution.
     * The method takes a list of Measurement Objects (1 or more), a TrueSightCredentials object
     * and the sleep time in milliseconds that the system need to wait during a retry.
     * 
     * @param measures List of Measurement objects which need to be pushed to TrueSight.
     * @param credentials   Basic authentication credentials required for REST API call.
     * @param retrySleepTime Number of milliseconds the client has to wait during a retry.
     * @return TrueSightClientResponse  Provides HTTP status code and JSON response from the API.
     * @throws TrueSightClientException Wraps other exceptions thrown during the method execution.
     * @see TrueSightClientResponse
     * @see Measurement
     * @see TrueSightCredentials
     */
    public TrueSightClientResponse addMeasures(List<Measurement> measures, TrueSightCredentials credentials, int retrySleepTime) throws TrueSightClientException {
        
        LOG.info("Inovking addMeasures() ...");
        TrueSightClientResponse result = null;
        if(measures != null && measures.size() > 0) 
        {
            LOG.debug("getMeasures() called with {} Measurements", measures.size());
            List<Object> bulkMeasures = TrueSightAPIClientUtility.toBulkEntryMeasureFormat(measures);            
            String endpoint = TrueSightAPIClientUtility.getHost() + Configuration.MEASURE_ENDPOINT;
            result = TrueSightAPIClientUtility.executePost(bulkMeasures, credentials, endpoint, retrySleepTime);
            LOG.debug("ClientResponse: {}", result.toString());
        }
        else
        {
            LOG.error("Failed to add Measurement: Measurement list is null or empty");
            throw new TrueSightClientException("Failed to add Measurement: Measurement list is null or empty");
        }
        
        return result;
    }  
    
    
    /**
     * addMeasures() invoking the API /v1/batch/metrics.
     * returns a TrueSightClientResponse on successful execution.
     * The method takes a list of Metric Objects (1 or more), a TrueSightCredentials object
     * and the sleep time in milliseconds that the system need to wait during a retry.
     * 
     * @param metrics List of Metric objects which need to be pushed to TrueSight.
     * @param credentials   Basic authentication credentials required for REST API call.
     * @param retrySleepTime Number of milliseconds the client has to wait during a retry.
     * @return TrueSightClientResponse  Provides HTTP status code and JSON response from the API.
     * @throws TrueSightClientException Wraps other exceptions thrown during the method execution.
     * @see TrueSightClientResponse
     * @see Metric
     * @see TrueSightCredentials
     */
    public TrueSightClientResponse createMetrics(List<Metric> metrics, TrueSightCredentials credentials, int retrySleepTime) throws TrueSightClientException {
        
        TrueSightClientResponse result = null;
        if(metrics != null && metrics.size() > 0)
        {
            //Converting the List<Metrics> to List<Object>.
            List<Object> objectList = new ArrayList<>(metrics);            
            String endpoint = TrueSightAPIClientUtility.getHost() + Configuration.METRIC_ENDPOINT;
            result = TrueSightAPIClientUtility.executePost(objectList, credentials, endpoint, retrySleepTime);
        } 
        else
            throw new TrueSightClientException("Failed to add Metric: Metric list is null or empty");
                
        return result;         
    }    
    
    
    
    /**
     * addMeasures() invoking the API /v1/events.
     * returns a TrueSightClientResponse on successful execution.
     * The method takes a list of Event Objects (1 or more), a TrueSightCredentials object
     * and the sleep time in milliseconds that the system need to wait during a retry.
     * 
     * @param events List of Event objects which need to be pushed to TrueSight.
     * @param credentials   Basic authentication credentials required for REST API call.
     * @param retrySleepTime Number of milliseconds the client has to wait during a retry.
     * @return TrueSightClientResponse  Provides HTTP status code and JSON response from the API.
     * @throws TrueSightClientException Wraps other exceptions thrown during the method execution.
     * @see TrueSightClientResponse
     * @see Event
     * @see TrueSightCredentials
     */
    public TrueSightClientResponse createEvents(List<Event> events, TrueSightCredentials credentials, int retrySleepTime) throws TrueSightClientException {
        
        TrueSightClientResponse result = null;
        if(events != null && events.size() > 0)
        {
            List<Object> objectList = new ArrayList<>(events);
            String endpoint = TrueSightAPIClientUtility.getHost() + Configuration.EVENT_ENDPOINT;
            result = TrueSightAPIClientUtility.executePost(objectList, credentials, endpoint, retrySleepTime);
        }
        else
            throw new TrueSightClientException("Failed to create Event: Event list is null or empty");
        return result;
    }
}
