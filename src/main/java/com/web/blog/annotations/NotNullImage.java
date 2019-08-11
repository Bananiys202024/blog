package com.web.blog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=NotNullImag.class)
@Retention(RUNTIME)
@Target({ TYPE, FIELD, PARAMETER })
@Documented
public @interface NotNullImage {
	
	//define default course code
	public String value() default "image";
	
	//define default message
	public String message() default "Image can't be null";
	
	//define default groups
	public Class<?>[] groups() default{};
	
	//define default payload
	public Class<? extends Payload>[] payload() default{};
	
	
}
