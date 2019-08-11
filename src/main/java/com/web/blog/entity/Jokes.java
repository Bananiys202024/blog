package com.web.blog.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.repository.CountQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(value = "jokes")
@AllArgsConstructor
@Builder
public @Data class Jokes {

	@PrimaryKey
    @Column(value = "id")
	@NotNull private UUID id;

	@Column(value = "createdd")
	@NotNull private Date createdd;
	
	@Column(value = "jokes")
	@NotNull private String jokes;
	
	@Column(value = "deleted")
	@NotNull private boolean deleted;

	@Column(value="count")
	@NotNull private Long count;
	
	@Transient
	private String timeago;

	public Jokes(UUID id){
		this.id=id;
	}
	
}
