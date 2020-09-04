package com.enosh.backtoback.model;

import java.util.Map;

public class LocationDto {

    private Map<String, String> street;
    private String city;
    private String country;

    public LocationDto(Map<String, String> street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public LocationDto(){}

    public Map<String, String> getStreet() {
        return street;
    }

    public void setStreet(Map<String, String> street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "LocationDto{" +
                "street=" + street +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
