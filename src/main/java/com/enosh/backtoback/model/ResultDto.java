package com.enosh.backtoback.model;

import java.util.Map;

public class ResultDto {

    private Map<String, String> name;
    private String gender;
    private String email;
    private Map<String, String> picture;
    private LocationDto location;

    public ResultDto(Map<String, String> name, String gender, String email, Map<String, String> picture, LocationDto location) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.picture = picture;
        this.location = location;
    }

    public ResultDto(){}

    public Map<String, String> getName() {
        return name;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getPicture() {
        return picture;
    }

    public void setPicture(Map<String, String> picture) {
        this.picture = picture;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "name=" + name +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", picture=" + picture +
                ", location=" + location +
                '}';
    }
}
