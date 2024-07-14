package com.microservices.services.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mst_Subject extends BaseStandartModel {
	
	private Long id;
    private String subject_name;

	
}
