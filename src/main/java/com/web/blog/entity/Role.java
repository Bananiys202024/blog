package com.web.blog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.web.blog.UDT.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(value = "role")
@AllArgsConstructor
public @Data class Role {
   
	@PrimaryKey
    @Column(value = "id")
	@NotNull private UUID id;

	@Column(value = "name")
    private String name;

	private String role; 
	
    @ManyToMany
    private Set<User> users;

   
}
