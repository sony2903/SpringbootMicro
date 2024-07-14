package com.microservices.services.model;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMdlPagination {
    public static String SUCCESS = "Success";
    
    private String code;
    private String message;
    // @JsonIgnore
    private Page<Mst_User> data;

}
