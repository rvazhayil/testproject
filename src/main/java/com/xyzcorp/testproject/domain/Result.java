package com.xyzcorp.testproject.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class Result {
    private final List<User> results;
    public Result(List<User> users){
        results = users;
    }
}
