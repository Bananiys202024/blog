package com.web.blog.UDT;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

//we use this model for creating entity Role;
@UserDefinedType(value = "user")
public class User {

	private UUID id;
	
	private String username;
	
	private String password;

}
