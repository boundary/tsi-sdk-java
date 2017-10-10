/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.samples;

import com.bmc.truesight.apiclient.client.TrueSightAPIClient;
import com.bmc.truesight.apiclient.dto.Measurement;
import com.bmc.truesight.apiclient.dto.TrueSightClientResponse;
import com.bmc.truesight.apiclient.dto.TrueSightCredentials;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to test the addMeasures() method.
 * The values are hard-coded. please change it accordingly for testing.
*/
public class TestMeasuresClient {

    public static void main(String args[])
    {
        TrueSightCredentials creds =new TrueSightCredentials("<email-ID>", "<api-token>");

        TrueSightAPIClient client = new TrueSightAPIClient(creds, 31000);

        //Creating the instance of Measurement object with the mandatory fields.
        //timestmap long value need to be changed to see the current value.
        Measurement measure1 = new Measurement("MyTestSource1", "TS.API.CLIENT.TEST.NUMBER1", 1300, 1506330446, "MyApp");
        Measurement measure2 = new Measurement("MyTestSource2", "TS.API.CLIENT.TEST.NUMBER1", 2200, 1506330500, "MyApp");

        measure1.setOrigin("LocalAPIClient");
        measure1.setOrigin("LocalAPIClient");

        try
        {
            List<Measurement> measures = new ArrayList<>();
            measures.add(measure1);
            measures.add(measure2);

            //Invoking the method for creating measures.
            TrueSightClientResponse res = client.addMeasures(measures);
            System.out.println(res);
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
