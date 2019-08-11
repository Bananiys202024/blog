package com.web.blog.util;

import com.web.blog.entity.Diary;

import ch.qos.logback.classic.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;


public class ReflectionJava {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ReflectionJava.class);
	
	public static List<String> getNameOfColumn(Diary diary) {
		
		    List<String> list = new ArrayList<String>();
		    list.add("#");
		    try 
		    {
	        Field[] allFields = diary.getClass().getDeclaredFields();

	        for (Field each : allFields) {
	        	
	            if(excludeDateAndId(each)){

	                Field field = diary.getClass().getDeclaredField(each.getName());
	                field.setAccessible(true);

	                list.add(String.valueOf(field.getName()));
	            }

	        }
	        
		    }
		    catch(Exception e)
		    {
		    	logger.info("Ops",e);
		    }
	        
		return list;
	}

	private static boolean excludeDateAndId(Field each) {
		

		if(each.getType().toString().equals("class java.util.UUID")) { return false;} 
		
		if(each.getType().toString().equals("class java.util.Date")) { return false;}
		

		return true;
	}

}
