package com.web.blog.entity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(value = "goals")
@AllArgsConstructor
@Builder
public @Data class Goals implements Serializable {

	@PrimaryKey
    @Column(value = "id")
	@NotNull private UUID id;

	@Column(value = "createdd")
	@NotNull private Date createdd;
	
	@Column(value = "editedd")
	@NotNull private Date editedd;
	
	@Column(value = "title")
	@NotNull private String title;
	
	@Column(value = "description")
	@NotNull private String description;
	
	@Lob
	@Column(value = "image")
	@NotNull private ByteBuffer image;
	
	@Transient
	private String timeago;
	
	
}
