package com.microservices.services.model.Response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomPage<T> {
    private int totalPages;
    private long totalElements;
    private int number;
    private int size;
    private List<T> content;
}