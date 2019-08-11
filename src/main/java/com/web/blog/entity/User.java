package com.web.blog.entity;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.web.blog.UDT.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(value = "user")
@AllArgsConstructor
@Builder
public @Data class User {
   
	@PrimaryKey
    @Column(value = "id")
	@NotNull private UUID id;

	@Column(value = "username")
    private String username;

	@Column(value = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(mappedBy = "roles")
    private Set<Role> roles;

}
