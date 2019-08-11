package com.web.blog.dao;

import java.util.Date;

import org.springframework.data.repository.query.Param;

import com.web.blog.entity.Diary;

public interface CustomizedDiary<Diary> {

	int existsByDate(@Param("date") Date date);
	
}
