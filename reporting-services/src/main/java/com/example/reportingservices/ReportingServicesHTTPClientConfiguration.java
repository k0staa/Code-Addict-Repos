package com.example.reportingservices;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Michal Kostewicz on 23.01.18.
 */
@Configuration
public class ReportingServicesHTTPClientConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(httpClient());
        template.setRequestFactory(requestFactory);

        return template;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        String user = "UserName";
        String password = "userPass";
        String domain = "USER_DOMAIN";
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new NTCredentials(user, password, null, domain));

        CloseableHttpClient httpclient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        return httpclient;
    }
}


