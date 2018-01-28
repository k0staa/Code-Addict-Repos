package com.example.reportingservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingServicesClientTest {
    @Autowired
    private ReportingServicesClient reportingServicesClient;

    @Test
    public void getReport_givenSimpleReportName_returnReportByteArray() throws Exception {
        //given
        String reportNamePath = "/RaportTestowy/Departments";
        String reportFormat = "PDF";

        //when
        byte[] reportByteArray = reportingServicesClient.getReport(reportNamePath, reportFormat);

        //then
        assertTrue(reportByteArray.length > 0);
    }

}