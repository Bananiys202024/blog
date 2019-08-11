package com.web.blog.UDT;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;


//we use this model for creating entity User;

@UserDefinedType(value = "role")
public @Data class Role {

	private UUID id;
	
	private String name;
	
}
