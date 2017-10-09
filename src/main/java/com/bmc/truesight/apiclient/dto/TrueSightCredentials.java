/*
Copyright 2017 BMC Software, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
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
