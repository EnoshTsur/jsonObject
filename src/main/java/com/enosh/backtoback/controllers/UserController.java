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
        return ResponseEntity.ok(
                Map.of(
                        "name", userService.extractFullName(),
                        "gender", userService.getStringAttribute("gender"),
                        "email", userService.getStringAttribute("email"),
                        "picture", userService.extractPicture()
                )
        );
    }


}
