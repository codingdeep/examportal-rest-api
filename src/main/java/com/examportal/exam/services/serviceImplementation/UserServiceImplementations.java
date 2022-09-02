package com.examportal.exam.services.serviceImplementation;

import com.examportal.exam.exceptions.ResourceNotFoundException;
import com.examportal.exam.models.Role;
import com.examportal.exam.models.User;
import com.examportal.exam.models.UserRole;
import com.examportal.exam.payloads.UserDto;
import com.examportal.exam.repository.UserRepository;
import com.examportal.exam.services.UserServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import javax.annotation.Resource;

import com.examportal.exam.exceptions.ResourceExistingException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.examportal.exam.repository.RoleRepository;

@Service
public class UserServiceImplementations implements UserServices {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    //creating a user
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User duplicateData = this.userRepository.findByEmail(user.getEmail());

        if (duplicateData == null) {
            for (UserRole ur: user.getUserRoles()){
                this.roleRepository.save(ur.getRole());
                ur.setUser(user);
            }
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
            user = this.userRepository.save(user);

        } else {
            throw new ResourceExistingException("User", "email", user.getEmail());
        }
        return this.modelMapper.map(user, UserDto.class);
    }


    //updating  user from admin
    @Override
    public UserDto updateUser(UserDto userDto, Long userId){
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId.toString()));

        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setBio(userDto.getBio());
        user.setPhone(userDto.getPhone());

        user = this.userRepository.save(user);
        return this.modelMapper.map(user, UserDto.class);
    }

    //updating normal user
    @Override
    public UserDto updateMe(UserDto userDto){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = this.modelMapper.map(userDetails, User.class);
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setBio(userDto.getBio());
        User user1 = this.userRepository.save(user);
        return this.modelMapper.map(user1,UserDto.class);
    }

    //deleting a user
    @Override
    public void deleteUser(Long userId){
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId.toString()));
        this.userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser(){
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

}
