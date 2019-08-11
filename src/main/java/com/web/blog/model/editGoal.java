package com.web.blog.model;

import java.nio.ByteBuffer;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.web.blog.annotations.NotNullImage;

import lombok.Data;

public @Data class editGoal {

//	@NotNullImage
	private MultipartFile imageT;
	
	@NotNull
    @Size(min=5, max=60)  
	private String goal;
	
	@NotNull
	@Size(min=1, max=200, message = "size must be between 1 and 200, paragraphs counts") 
	private String description;

	private String id;
	
	private Date createdd;
	
	
}
