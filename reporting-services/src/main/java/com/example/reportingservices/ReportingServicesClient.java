package com.example.reportingservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Michal Kostewicz on 23.01.18.
 */
@Component
public class ReportingServicesClient {
    private static String FORMAT_URL_PART = "&rs:Format=";
    private static String REPORTING_SERVICES_URL =
            "http://ReportingServicesDomainOrUrl/ConfiguredUrl?";
    @Autowired
    private RestTemplate restTemplate;


    public byte[] getReport(String reportName, String reportFormat) {
        HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
        String reportURL = REPORTING_SERVICES_URL + reportName + FORMAT_URL_PART + reportFormat;
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(reportURL, HttpMethod.GET,
                requestEntity, byte[].class);
        return responseEntity.getBody();
    }


}

