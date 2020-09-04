package com.enosh.backtoback.controllers;

import com.enosh.backtoback.common.UserService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpMethod.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, String>> getData() {
        JSONObject result = userService.getResult();

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
