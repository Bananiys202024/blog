package com.web.blog.util;

import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.entity.Jokes;

public class Exclude {

	public static List<Jokes> exscludeDeletedJokes(List<Jokes> jokes) {
	
		return jokes.stream()	               
					.filter(j -> Boolean.FALSE.equals(j.isDeleted()))
					.collect(Collectors.toList());         

	}
}
