package com.microservices.services.model.Response;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservices.services.model.Mst_Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMdlSubjectPagination {
    // @JsonIgnore
    public static String SUCCESS = "Success";

    private String code;
    private String message;
    private Page<Mst_Subject> data;

}
