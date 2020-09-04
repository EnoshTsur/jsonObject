package com.enosh.backtoback.common;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Service
public class UserService {

    private final String RESULTS = "results";

    @Value("${service-url}")
    private String serviceUrl;

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private JSONObject getResult() {
        return new JSONObject(
                restTemplate.exchange(
                        serviceUrl,
                        GET,
                        null,
                        String.class
                ).getBody()
        )
                .getJSONArray(RESULTS)
                .getJSONObject(0);
    }
}
