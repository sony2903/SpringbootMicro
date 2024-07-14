package com.microservices.services.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMdl {
    @JsonIgnore
    public static String SUCCESS = "Success";

    private String code;
    private String message;

}
