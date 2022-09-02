package com.examportal.exam.controllers;

import com.examportal.exam.payloads.JWTRequest;
import com.examportal.exam.payloads.JWTResponse;
import com.examportal.exam.security.JWTTokenHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Resource
    private JWTTokenHelper jwtTokenHelper;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/user/login")
    public ResponseEntity<JWTResponse> createToken(@RequestBody() JWTRequest jwtRequest) {
        this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JWTResponse response = new JWTResponse(token);
        return ResponseEntity.ok().body(response);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        this.authenticationManager.authenticate(authenticationToken);

    }


}
