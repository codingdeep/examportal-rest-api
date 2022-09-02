package com.examportal.exam.services;

import com.examportal.exam.exceptions.ResourceExistingException;
import com.examportal.exam.models.User;
import com.examportal.exam.models.UserRole;
import com.examportal.exam.payloads.UserDto;

import java.util.List;
import java.util.Set;

public interface UserServices {

    //create user
    UserDto createUser(UserDto userDto) throws ResourceExistingException;

    //update user
    UserDto updateUser(UserDto userDto, Long userId);


    //delete the user
    public void deleteUser(Long userId);

    //delete the user
    public UserDto updateMe(UserDto userDto);

    //getting all the users
    public List<UserDto> getAllUser();


}
