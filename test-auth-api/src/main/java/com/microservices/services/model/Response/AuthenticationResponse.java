package com.microservices.services.model.Response;

import com.microservices.services.model.ResponseMdl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends ResponseMdl {
	
	private String token;

	
}
