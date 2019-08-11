package com.web.blog.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.web.blog.entity.Jokes;

public interface CustomizedJokes<Jokes> {

	Jokes findRandomJoke(@Param("count") Long count);

	long askMaxCount();
	
}
