package com.microservices.services.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import com.microservices.services.config.JwtUtil;
import com.microservices.services.config.SecurityConfig;
import com.microservices.services.model.Mst_User;
import com.microservices.services.model.ResponseMdl;
import com.microservices.services.model.Request.CreateUserRequest;
import com.microservices.services.model.Request.SignModel;
import com.microservices.services.model.Response.AuthenticationResponse;
import com.microservices.services.service.SigninService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SigninService signinService;

    @Autowired
    private SecurityConfig sc;

    @PostMapping("auth/login")
    public ResponseEntity<?> createAuthenticationToken(@Validated @RequestBody SignModel authenticationRequest) throws Exception {
        
        ResponseMdl res = new ResponseMdl();
        // Check if user exists
        Mst_User user = signinService.find(authenticationRequest.getUser_code());
        if (user == null) {
            
            res.setMessage("User not found");
            return ResponseEntity.badRequest().body(res);
        }

        // Compare passwords
        
        if (!sc.passwordEncoder().matches(authenticationRequest.getPassword(), user.getPassword())) {
            res.setMessage("Incorrect password");
            return ResponseEntity.badRequest().body(res);
        }

        // Generate JWT token
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUser_code(), user.getPassword(), new ArrayList<>());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authRes = new AuthenticationResponse();
        authRes.setToken(jwt);
        return ResponseEntity.ok(authRes);
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody CreateUserRequest req) throws Exception {
        
        ResponseMdl res = new ResponseMdl();
        // Check if user exists
        Mst_User user = signinService.find(req.getUser_code());
        if (user != null) {
            
            res.setMessage("User Code already use");
            return ResponseEntity.badRequest().body(res);
        }

        try {
            req.setPassword(sc.passwordEncoder().encode(req.getPassword()));
            signinService.create(req);
            res.setMessage("Register Succeed");

            return ResponseEntity.ok(res);
            
        } catch (Exception e) {
            // TODO: handle exception
            res.setMessage(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(res);

        }
    }
}

