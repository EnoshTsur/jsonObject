package com.enosh.backtoback.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

import static com.enosh.backtoback.services.UserConstants.*;
import static org.springframework.http.HttpMethod.GET;

@Service
public class UserService {

    @Value("${service-url}")
    private String serviceUrl;

    private final RestTemplate restTemplate;

    private JSONObject results;

    @PostConstruct
    private void init(){
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

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String extractFullName() {
        JSONObject name = results.getJSONObject(NAME);
        return name.getString(FIRST) + " " + name.getString(LAST);
    }

    private String getStringAttribute(String key) {
        return results.getString(key);
    }

    private String extractPicture() {
        return results
                .getJSONObject(PICTURE)
                .getString(MEDIUM);
    }

    private JSONObject getLocation() {
        return results.getJSONObject(LOCATION);
    }

    private JSONObject extractStreet() {
        return getLocation().getJSONObject(STREET);
    }

    public Map<String, String> getUserDetails() {
        return Map.of(
                NAME, extractFullName(),
                GENDER, getStringAttribute(GENDER),
                EMAIL, getStringAttribute(EMAIL),
                PICTURE, extractPicture()
        );
    }
}
