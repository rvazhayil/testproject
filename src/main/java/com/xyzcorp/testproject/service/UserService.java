package com.xyzcorp.testproject.service;

import com.xyzcorp.testproject.domain.UserDto;
import com.xyzcorp.testproject.persistence.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers(float min, float max, Pageable pageable);
    public void addUsers(List<UserDto> userDtos);
}
