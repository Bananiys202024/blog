package com.web.blog.model;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.web.blog.entity.Notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class AddNote {

	private UUID id;

	private Date createdd;
	
	@NotNull 
	@NotBlank
	private String note;
	
	private boolean delete;
	
}
