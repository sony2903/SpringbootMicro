package com.microservices.services.model.Request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseRequest {

    // @NotBlank(message = "User Code is required")
    private String user_code;

}

