package com.web.blog.model;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.cassandra.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data class AddValuesToDiary {

	private int Java;
	
	private int Electro;
	
	private int University;
	
	private int Sport;
	
	private int English;
	
	private int German;
	
	private int InterviewJava;
	
	private int InterviewElectro;
	
	private int XaXa;
	
	
}
