package com.microservices.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.tomcat.jni.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_user", uniqueConstraints = {
    @javax.persistence.UniqueConstraint(columnNames = "user_code")
})
public class Mst_User extends BaseStandartModel {
	
	@Id
	private Long id;
	
    @NotBlank(message = "User code is required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "User code should contain only alphanumeric characters")
    @Column(unique = true)
    private String user_code;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "User name should contain only alphabetic characters")
    private String name;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String password;

    public enum UserRole {
        TEACHER,
        STUDENT
    }

	
}
