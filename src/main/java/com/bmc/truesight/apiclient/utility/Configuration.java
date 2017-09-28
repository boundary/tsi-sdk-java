/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.utility;


public interface Configuration 
{
    public static final String MEASURE_ENDPOINT = "/v1/measurements";
    public static final String METRIC_ENDPOINT = "/v1/batch/metrics";
    public static final String EVENT_ENDPOINT = "/v1/events"; 
    public static String DEFAULT_HOSTNAME = "https://api.truesight.bmc.com";
}
