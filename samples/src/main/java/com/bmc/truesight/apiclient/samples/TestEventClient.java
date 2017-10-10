/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.samples;

import com.bmc.truesight.apiclient.client.TrueSightAPIClient;
import com.bmc.truesight.apiclient.dto.Event;
import com.bmc.truesight.apiclient.dto.EventSource;
import com.bmc.truesight.apiclient.dto.TrueSightClientResponse;
import com.bmc.truesight.apiclient.dto.TrueSightCredentials;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to test the createEvent() method.
 * The values are hard-coded. please change it accordingly for testing.
*/
public class TestEventClient {

    public static void main (String args[])
    {
        //Creating the credential object which will be used while using the API.
        TrueSightCredentials creds =new TrueSightCredentials("<email-ID>", "<api-token>");
        TrueSightAPIClient client = new TrueSightAPIClient(creds, 31000);

        List<Event> events = new ArrayList<>();

        List<String> fp1= new ArrayList<>();
        fp1.add("@Title");
        //EventSource es1 = new EventSource("Database11", "Alert11");
        EventSource es1 = new EventSource("", "Alert11");

        //Instantiating the Event object with required parameters. Optional values are set seperately.
        Event e1 = new Event("MyTitle11", fp1, es1);
        e1.setCreatedAt(1506251154L);
        e1.setMessage("EventMessage1");
        e1.setSeverity("ERROR");
        e1.setStatus("CLOSED");
        Map<String, Object> props = new HashMap<>();
        props.put("prop1", "Value1");
        props.put("Prop2", 100);
        e1.setProperties(props);
        events.add(e1);

        List<String> fp2= new ArrayList<>();
        fp2.add("@Title");
        EventSource es2 = new EventSource("Database21", "");

        //Instantiating the Event class with the required parameters.
        Event e2 = new Event("MyTitle2", fp2, es2);
        e2.setEventClass("MyEvent");
        EventSource es21 = new EventSource("Network", "Connection");
        es21.setName("SenderName");
        Map<String, Object> sp = new HashMap<>();
        sp.put("one", "OneValue");
        sp.put("two", 200);
        es21.setProperties(sp);
        e2.setSender(es21);
        List<String> t = new ArrayList<>();
        t.add("Tag1");
        t.add("Tag2");
        e2.setTags(t);
        events.add(e2);

        try
        {
            //Invoking the client method for creating events.
            TrueSightClientResponse res = client.createEvents(events);
            System.out.println(res);
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

}