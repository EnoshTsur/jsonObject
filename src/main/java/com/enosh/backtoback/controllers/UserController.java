package com.enosh.backtoback.controllers;

import com.enosh.backtoback.model.UserDto;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpMethod.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, String>> getData() {
        JSONObject result = new JSONObject(
                restTemplate.exchange(
                        "https://randomuser.me/api",
                        GET,
                        null,
                        String.class
                ).getBody()
        )
                .getJSONArray("results")
                .getJSONObject(0);

        JSONObject name = result.getJSONObject("name");

        return ResponseEntity.ok(
                Map.of(
                        "name", extractFullName(name),
                        "gender", result.getString("gender"),
                        "email", result.getString("email"),
                        "picture", result.getJSONObject("picture")
                                .getString("medium")
                )
        );
    }

    private String extractFullName(JSONObject name) {
        return name.getString("first") + " " + name.getString("last");
    }
}
