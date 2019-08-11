package com.web.blog.entity;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Entity
@Table(value = "notes")
@AllArgsConstructor
@Builder
public @Data class Notes {

	@PrimaryKey
    @Column(value = "id")
	@NotNull private UUID id;

	@Column(value = "createdd")
	@NotNull private Date createdd;
	
	@Column(value = "note")
	@NotNull private String note;
	
	@Column(value = "delete")
	@NotNull private boolean delete;

	@Transient
	private String timeago;
	

}