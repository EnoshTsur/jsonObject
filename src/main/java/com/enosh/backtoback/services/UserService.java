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

    private JSONObject getResults() {
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

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String extractFullName(JSONObject results) {
        JSONObject name = results.getJSONObject(NAME);
        return name.getString(FIRST) + " " + name.getString(LAST);
    }

    private String getStringAttribute(JSONObject results, String key) {
        return results.getString(key);
    }

    private String extractPicture(JSONObject results) {
        return results
                .getJSONObject(PICTURE)
                .getString(MEDIUM);
    }

    private JSONObject getLocation(JSONObject results) {
        return results.getJSONObject(LOCATION);
    }

    private JSONObject extractStreet(JSONObject results) {
        return getLocation(results).getJSONObject(STREET);
    }

    public Map<String, String> getUserDetails() {
        JSONObject results = getResults();

        return Map.of(
                NAME, extractFullName(results),
                GENDER, getStringAttribute(results, GENDER),
                EMAIL, getStringAttribute(results, EMAIL),
                PICTURE, extractPicture(results),
                STREET_NAME, extractStreet(results).getString(NAME),
                STREET_NUMBER, String.valueOf(extractStreet(results).getInt(NUMBER)),
                CITY, getLocation(results).getString(CITY),
                COUNTRY, getLocation(results).getString(COUNTRY)
        );
    }
}
