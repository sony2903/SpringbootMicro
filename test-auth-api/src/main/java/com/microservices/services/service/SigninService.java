package com.microservices.services.service;

import com.microservices.services.model.Mst_User;
import com.microservices.services.model.Request.CreateUserRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SigninService {

    @Value("${api.user.service}")
    private String SERVER_USER_SERVICE;

    public Mst_User find(String request){
        String endpoint = SERVER_USER_SERVICE;
        endpoint = endpoint + "/user" + "?code=" + request;
        RestTemplate restTemplate = new RestTemplate();
        Mst_User responseEntity = restTemplate.getForObject(endpoint, Mst_User.class);
        return responseEntity;
    }

    public Mst_User create(CreateUserRequest request){
        String endpoint = SERVER_USER_SERVICE;
        endpoint = endpoint + "/user";
        RestTemplate restTemplate = new RestTemplate();
        Mst_User responseEntity = restTemplate.postForObject(endpoint, request, Mst_User.class);
        return responseEntity;
    }

}

