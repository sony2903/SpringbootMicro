package com.microservices.services.model.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectPaginationRequest {

    @NotNull
    @Min(0)
    Integer page;

    @NotNull
    @Min(1)
    Integer size;
	
}
