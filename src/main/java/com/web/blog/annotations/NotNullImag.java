package com.web.blog.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


public class NotNullImag implements ConstraintValidator<NotNullImage, MultipartFile> {
	
		@Override
	    public void initialize(NotNullImage notnullimage) {}
	 
		@Override
	    public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {
	    	return !image.isEmpty();
	    }

}
