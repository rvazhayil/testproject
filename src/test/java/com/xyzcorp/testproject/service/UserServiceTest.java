package com.xyzcorp.testproject.service;

import com.xyzcorp.testproject.domain.UserDto;
import com.xyzcorp.testproject.persistence.User;
import com.xyzcorp.testproject.persistence.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl underTest;
    private User user;
    private Page<User> page;
    private List<UserDto> userDtoList;
    @BeforeEach
    public void init(){
        user = User.builder().name("John").salary( 1000.01F).build();
        List<User> aList = new ArrayList<>();
        aList.add(user);
        page = new PageImpl(aList);
        underTest = new UserServiceImpl(userRepository);
        userDtoList = new ArrayList<>();
        userDtoList.add(new UserDto("John",1000.12F));
    }

    @Test
    public void whenGetUsers_thenSuccess(){
        Mockito.when(userRepository.findBySalaryBetween(anyFloat(),anyFloat(),any())).thenReturn(page);
        List<User> users = underTest.getAllUsers(0,2000, Pageable.ofSize(100));
        assertEquals(1,users.size());
        assertEquals(user,users.get(0));
    }

    @Test
    public void whenAddUsers_thenSuccess(){
        Mockito.when(userRepository.saveAll(any())).thenReturn(Collections.emptyList());
        underTest.addUsers(userDtoList);
        Mockito.verify(userRepository,Mockito.times(1)).saveAll(any());
    }

}