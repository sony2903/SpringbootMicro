package com.microservices.services.model.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.microservices.services.model.Mst_User;
import com.microservices.services.model.Mst_User.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest extends BaseRequest{
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Role is required")
    private UserRole role; // You can use enum here if needed
    
    @NotBlank(message = "Password is required")
    private String password;

	
}
