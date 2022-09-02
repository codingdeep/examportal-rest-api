/*
* filename: UserController
* author: Rubel Ahmmed
* @path: com.examportal.exam.controllers
* */

package com.examportal.exam.controllers;
import com.examportal.exam.payloads.UserDto;
import com.examportal.exam.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Resource
    private UserServices userServices;

    //creating user
    @PostMapping("/user/create")
    public ResponseEntity<UserDto> createUser(
            @Valid
            @RequestBody() UserDto userDto){
        UserDto userDto1 = this.userServices.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    //updating user information
    @PutMapping("/users/update/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody() UserDto userDto,
            @PathVariable("userId") Long userId
    ){
        UserDto updateUser = this.userServices.updateUser(userDto,userId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }
    //updating user information
    @PutMapping("/user/update/me")
    public ResponseEntity<UserDto> updateMe(
            @RequestBody() UserDto userDto
    ){
        UserDto updateUser = this.userServices.updateMe(userDto);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }

    //deleting user

    @DeleteMapping("/users/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userServices.deleteUser(userId);
    }

    //get all user
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtos = this.userServices.getAllUser();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }




}
