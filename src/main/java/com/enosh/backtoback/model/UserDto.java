package com.enosh.backtoback.model;
import java.util.List;

public class UserDto {

    private List<ResultDto> results;

    public UserDto(List<ResultDto> results) {
        this.results = results;
    }

    public UserDto(){}

    public List<ResultDto> getResults() {
        return results;
    }

    public void setResults(List<ResultDto> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "results=" + results +
                '}';
    }
}
