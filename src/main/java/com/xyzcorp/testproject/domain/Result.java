package com.xyzcorp.testproject.domain;

import com.xyzcorp.testproject.persistence.User;
import lombok.Getter;

import java.util.List;


@Getter
public class Result {
    private final List<User> results;
    public Result(List<User> users){
        results = users;
    }
}
