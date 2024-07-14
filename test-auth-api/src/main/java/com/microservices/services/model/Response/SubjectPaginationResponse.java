package com.microservices.services.model.Response;

import org.springframework.data.domain.Page;

import com.microservices.services.model.Mst_Subject;
import com.microservices.services.model.ResponseMdl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectPaginationResponse extends ResponseMdl {

    CustomPage<Mst_Subject> data;
	
}
