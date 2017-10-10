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
package com.bmc.truesight.apiclient.utility;

import com.bmc.truesight.apiclient.dto.Measurement;
import com.bmc.truesight.apiclient.dto.TrueSightClientResponse;
import com.bmc.truesight.apiclient.dto.TrueSightCredentials;
import com.bmc.truesight.apiclient.exception.TrueSightClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TrueSightAPIClientUtility {
    
    private static final Logger LOG = LoggerFactory.getLogger(TrueSightAPIClientUtility.class);

    /* addMeasures API expects the payload in a specific format List<Object>.    
     * It also has as format without the the field names. This need special processing.
     * The method takes in List<Measurement> and returns List<Object>
     * AppID field is options with the API but was asked to be made mandatory.
     * Hence, this was made part of the mandatory fields during instantiation.
    */
    public static List<Object> toBulkEntryMeasureFormat(List<Measurement> measures)
    {        
        List<Object> bulkMeasures = new ArrayList<>();
        measures.forEach((m) -> 
        {            
            List<Object> values = new ArrayList<>();
            values.add(m.getSource());
            values.add(m.getMetric());
            values.add(m.getMeasure());
            values.add(m.getTimestamp());
            
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("app_id", m.getAppID());
            if(m.getOrigin().isPresent())
                metadata.put("origin", m.getOrigin().get());
            
            values.add(metadata);
            bulkMeasures.add(values);
        }                
        );
        LOG.debug("BulkMeasures size: {}", bulkMeasures.size());
        return bulkMeasures;
    }

    // The method which invokes the http call.
    // Returns the response from the API call if succeeds, thows exception otherwise.
    public static TrueSightClientResponse executePost(List<Object> payload, TrueSightCredentials creds, String endpoint, int retrySleepTime) throws TrueSightClientException
    {        
        LOG.debug("Ready to execute POST for enpoint {}", endpoint);
        TrueSightClientResponse result= null;
        
        //Registering Jdk8Module is required for the ObjectMapper to deal with Optioanl fields.
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());        
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(endpoint);             
        httpPost.addHeader("Authorization", "Basic " + encodeBase64(creds.toString()));
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("User-Agent", "TrueSightAPIClient");
        Charset charsetD = Charset.forName("UTF-8");
        
        //Setting the minimum sleep time to 30000.
        int interval = retrySleepTime > 30000 ? retrySleepTime: 30000;
        
        int retry = 3;
        int retryCount = 0;
        HttpResponse response = null;
        boolean done = false;
        while(!done)
        {            
            retryCount++;
            LOG.debug("Execution count: {}", retryCount);
            try
            {
                String jsonInString = mapper.writeValueAsString(payload);
                StringEntity postingString = new StringEntity(jsonInString, charsetD);            
                httpPost.setEntity(postingString);            
                        
                response = client.execute(httpPost);                
            }catch (IOException e) {  
                LOG.error("Exception for retry count: {} is: {}", retryCount, e.toString());
                if(retryCount < retry)
                {
                    try {                        
                        Thread.sleep(interval);
                    } catch (InterruptedException e1) {}
                    continue;
                }
                else
                {
                    done = true;                    
                    throw new TrueSightClientException(e.toString());
                }
            }
            //Retry only if there is an exception. The response is returned if successfully executed.
            done = true;
            result = updateResponseObject(response);
        }
        return result;
    }
    
    // Base64 encoder for Basic authetication.
    private static String encodeBase64(final String encodeToken) {
        byte[] encoded = Base64.encodeBase64(encodeToken.getBytes());
        return new String(encoded);
    }

    // The SDK by default points to production url. This is used to configure the SDK
    // to point to staging or dev environments if required.
    // Need to specify the environement variable 'TRUESIGHT-HOST-URL' in that case.
    public static String getHost() {
        
        String hostName = System.getenv("TRUESIGHT-HOST-URL");        
        if((null == hostName) || (hostName.length() == 0))
            hostName = Configuration.DEFAULT_HOSTNAME;
        
        LOG.debug("Host: {}", hostName);        
        return hostName;        
    }

   //Parses the response object return from the REST call and wraps it inside TrueSightClientResponse
    private static TrueSightClientResponse updateResponseObject(HttpResponse response) {
        
        TrueSightClientResponse res = null;
        int statusCode = response.getStatusLine().getStatusCode();
        
        HttpEntity entity = response.getEntity();
        if(entity != null)
        {   
            BufferedReader reader = null;
            try
            {
                String line = "";
                StringBuilder responseString = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                while ((line = reader.readLine()) != null)
                    responseString.append(line);                
                
                res = new TrueSightClientResponse(statusCode, responseString.toString());
                //reader.close();
            }catch(IOException ioe)
            {
                LOG.debug("Exception while parsing the response object");
                res = new TrueSightClientResponse(statusCode, "Response parsing error");
            }
            finally
            {
                if(reader != null)
                    try
                    {
                        reader.close();
                    }catch (Exception e){}                    
            }                     
        }
        return res;
    }
}
