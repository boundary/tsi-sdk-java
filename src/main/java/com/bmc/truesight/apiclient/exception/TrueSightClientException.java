package com.bmc.truesight.apiclient.exception;

/**
 * Base exception that wraps all other exceptions throw from SDK.
 */
public class TrueSightClientException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public TrueSightClientException(String message)
    {
        super(message);
    }
    
}
