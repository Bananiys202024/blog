package com.web.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.annotations.NotNullImage;
import com.web.blog.entity.Goals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class addNewGoal implements Serializable {

	List<Goals> allList = new ArrayList<>();
	
	@NotNullImage
	private MultipartFile image;
	
	@NotNull
    @Size(min=5, max=60)  
	private String goal;
	
	@NotNull
	@Size(min=1, max=200, message = "size must be between 1 and 200, paragraphs counts") 
	private String description;

	private String id;
	private Date createdd;
	
}
