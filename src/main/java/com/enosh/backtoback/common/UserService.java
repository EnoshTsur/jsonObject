package com.enosh.backtoback.common;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Service
public class UserService {

    @Value("${service-url}")
    private String serviceUrl;

    private final String RESULTS = "results";
    private final String NAME = "name";
    private final String FIRST = "first";
    private final String LAST = "last";
    private final String PICTURE = "picture";
    private final String MEDIUM = "medium";

    private final RestTemplate restTemplate;

    private final JSONObject results;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.results = new JSONObject(
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


    public String extractFullName() {
        JSONObject name = results.getJSONObject(NAME);
        return name.getString(FIRST) + " " + name.getString(LAST);
    }

    public String getStringAttribute(String key) {
        return results.getString(key);
    }

    public String extractPicture() {
        return results
                .getJSONObject(PICTURE)
                .getString(MEDIUM);
    }
}
