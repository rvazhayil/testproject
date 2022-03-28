package com.xyzcorp.testproject.web;

import com.xyzcorp.testproject.domain.FileUploadResult;
import com.xyzcorp.testproject.domain.Result;
import com.xyzcorp.testproject.domain.UserDto;
import com.xyzcorp.testproject.common.FileUploader;
import com.xyzcorp.testproject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;
    private FileUploader fileUploader;



    @PostMapping(path = "/upload")
    public ResponseEntity<FileUploadResult> uploadUserFile(@RequestParam("file") MultipartFile file){
        List<UserDto> userDtos = fileUploader.upload(file);
        userService.addUsers(userDtos);
        return ResponseEntity.ok(FileUploadResult.builder().success("1").build());

    }


    @GetMapping
    public ResponseEntity<Result> getUsers(@RequestParam(defaultValue = "0.0") float min, @RequestParam(defaultValue = "4000.0") float max,
                                              @PageableDefault(page = 0, size = 100)
                                              Pageable pageable) {

        return ResponseEntity.ok(new Result(userService.getAllUsers(min,max, pageable)));
    }


}
