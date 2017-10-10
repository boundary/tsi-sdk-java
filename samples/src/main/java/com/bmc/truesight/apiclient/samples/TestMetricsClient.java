/*
 * Copyright (c) 2017 bmc.com
 * All rights reserved.
 */
package com.bmc.truesight.apiclient.samples;

import com.bmc.truesight.apiclient.client.TrueSightAPIClient;
import com.bmc.truesight.apiclient.dto.MetricAggregates;
import com.bmc.truesight.apiclient.dto.MetricUnits;
import com.bmc.truesight.apiclient.dto.Metric;
import com.bmc.truesight.apiclient.dto.TrueSightClientResponse;
import com.bmc.truesight.apiclient.dto.TrueSightCredentials;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to test the createMetrics() method.
 * The values are hard-coded. please change it accordingly for testing.
*/
public class TestMetricsClient {

    public static void main(String args[])
    {
        TrueSightCredentials creds =new TrueSightCredentials("<email-ID", "<api-token>");
        TrueSightAPIClient client = new TrueSightAPIClient(creds, 31000);

        //Creating Metric objects with required mandatory fields.
        List<Metric> metrics = new ArrayList<>();
        Metric m1 = new Metric("TEST-METRIC-393", "TEST-METRIC-393", "TEST-METRIC-393", MetricUnits.number, MetricAggregates.avg);
        Metric m2 = new Metric("TEST-METRIC-494", "TEST-METRIC-494", "TEST-METRIC-494", MetricUnits.number, MetricAggregates.avg);

        m1.setDescription("TEST-METRIC-393");

        m1.setDefaultResolutionMS(1000);
        m2.setDefaultResolutionMS(2000);

        m1.setIsDisabled(true);
        m2.setIsDisabled(false);

        m1.setType("public");
        m2.setType("private");

        m1.setUnitDescription("UDes1");
        m2.setUnitDescription("UDes2");

        metrics.add(m1);
        metrics.add(m2);

        try
        {
            //Invoking the client call for creating the metrics.
            TrueSightClientResponse res = client.createMetrics(metrics);
            System.out.println(res);
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
