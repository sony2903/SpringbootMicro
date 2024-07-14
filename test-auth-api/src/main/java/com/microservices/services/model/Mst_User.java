package com.microservices.services.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mst_User extends BaseStandartModel {
	
    private String user_code;
    private String name;
    private UserRole role;
    private String password;
    public enum UserRole {
        TEACHER,
        STUDENT
    }

	
}
