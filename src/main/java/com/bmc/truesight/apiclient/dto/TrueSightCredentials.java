/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.dto;

/**
 * HTTP Basic Authentication parameter object.
 */
public final class TrueSightCredentials {
    
    private final String email;
    private final String token;
    
    /**
     * @param email emailID used for registering with TruesSight.
     * @param token used as password (TrueSight API token provided with each account)
     */
    public TrueSightCredentials(String email, String token)
    {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    @Override
    //returns in basic authentication format.
    public String toString() {
        return email + ":" + token;
    }    
}
