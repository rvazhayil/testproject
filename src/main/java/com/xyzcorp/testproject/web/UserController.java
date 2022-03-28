package com.xyzcorp.testproject.web;

import com.xyzcorp.testproject.domain.Result;
import com.xyzcorp.testproject.domain.User;
import com.xyzcorp.testproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addShop(@RequestBody User user) {
        User userEntity = userService.addUser(user);
        return ResponseEntity.created(null)
                .body(userEntity);
    }
    /*
    @GetMapping
    public ResponseEntity<Result> getUsers(@RequestParam(defaultValue = "0.0") float min, @RequestParam(defaultValue = "4000.0") float max,
                                               @RequestParam(defaultValue = "0") Integer offset, @RequestParam(required = false) Integer limit,
                                               @RequestParam(required = false) String sort) {

        return ResponseEntity.ok(new Result(userService.getAllUsers(min,max, offset,limit,sort)));
    }*/

    @GetMapping()
    public ResponseEntity<Result> getUsersNew(@RequestParam(defaultValue = "0.0") float min, @RequestParam(defaultValue = "4000.0") float max,
                                              @PageableDefault(page = 0, size = 100)
                                              Pageable pageable) {

        return ResponseEntity.ok(new Result(userService.getAllUsers(min,max, pageable)));
    }


}
