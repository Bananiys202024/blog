package com.web.blog.model;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class createJoke {

	@NotNull
	@NotBlank
	@Size(min=5, max=400) 
	private String textarea;

}
