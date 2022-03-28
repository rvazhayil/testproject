package com.xyzcorp.testproject.service;

import com.xyzcorp.testproject.domain.UserDto;
import com.xyzcorp.testproject.persistence.User;
import com.xyzcorp.testproject.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public List<User> getAllUsers(float min, float max, Pageable pageable){


        Page<User> page = userRepository.findBySalaryBetween(min,max,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return Collections.emptyList();
        }

    }

    public void addUsers(List<UserDto> userDtos) {
        List<User> users = userDtos.stream()
                .filter(userDto -> userDto.getSalary() > 0)
                .map((userDto) -> User.builder().name(userDto.getName()).salary(userDto.getSalary()).build())
                .collect(Collectors.toList());
        userRepository.saveAll(users);
    }
}
