package com.xyzcorp.testproject.service;

import com.xyzcorp.testproject.domain.User;
import com.xyzcorp.testproject.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    /*
    public List<User> getAllUsers(float min, float max, Integer offset, Integer limit, String sort){

        if(Objects.isNull(limit)) limit = Integer.MAX_VALUE;
        Pageable pageable = PageRequest.of(offset,limit,(Objects.isNull(sort))? Sort.unsorted() : Sort.by(sort));
        Page<User> page = userRepository.findBySalaryBetween(min,max,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return Collections.emptyList();
        }

    }*/

    public List<User> getAllUsers(float min, float max, Pageable pageable){


        Page<User> page = userRepository.findBySalaryBetween(min,max,pageable);
        if(page.hasContent()){
            return page.getContent();
        }else{
            return Collections.emptyList();
        }

    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
