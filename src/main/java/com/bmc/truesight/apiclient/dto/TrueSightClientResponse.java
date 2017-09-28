/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.dto;

/**
 * Response object from all the client methods exposed as part of the SDK.
 */
public final class TrueSightClientResponse {
   
    private final int statusCode;
    private final String jsonResponse;
    
    /**
     * @param statusCode the HTTP status code returned by the REST API.
     * @param jsonResponse JSON response returned by the REST API.
     */
    public TrueSightClientResponse(int statusCode, String jsonResponse)
    {
        this.statusCode = statusCode;
        this.jsonResponse = jsonResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }  

    @Override
    public String toString() {
        return "TrueSightClientResponse{" + "statusCode=" + statusCode + ", jsonResponse=" + jsonResponse + '}';
    }
    
    
}
