package com.microservices.services.service;

import com.microservices.services.model.Mst_Subject;
import com.microservices.services.model.Mst_User;
import com.microservices.services.model.ResponseMdl;
import com.microservices.services.model.ResponseMdlPagination;
import com.microservices.services.model.Request.CreateSubjectRequest;
import com.microservices.services.model.Request.CreateUserRequest;
import com.microservices.services.model.Request.SubjectPaginationRequest;
import com.microservices.services.model.Response.CustomPage;
import com.microservices.services.model.Response.ResponseMdlSubjectPagination;
import com.microservices.services.model.Response.SubjectPaginationResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SubjectService {

    @Value("${api.subject.service}")
    private String SERVER_SUBJECT_SERVICE;

    HttpHeaders headers;

    public Mst_Subject create(CreateSubjectRequest request){
        String endpoint = SERVER_SUBJECT_SERVICE;
        endpoint = endpoint + "/subject";
        RestTemplate restTemplate = new RestTemplate();
        Mst_Subject responseEntity = restTemplate.postForObject(endpoint, request, Mst_Subject.class);
        return responseEntity;
    }

    public CustomPage<Mst_Subject> pagination(SubjectPaginationRequest request) {
        String endpoint = SERVER_SUBJECT_SERVICE + "/subject/all";
        RestTemplate restTemplate = new RestTemplate();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize());

        @SuppressWarnings("unchecked")
        CustomPage<Mst_Subject> customPage = restTemplate.getForObject(builder.toUriString(), CustomPage.class);        
        return customPage;
    }

}

