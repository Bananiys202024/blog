package com.web.blog.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.entity.Diary;
import com.web.blog.entity.Jokes;
import com.web.blog.entity.Notes;

public class Sorts {
	
	public static List<Notes> listByDate(List<Notes> notes) {
		return   notes.stream()
		        .sorted(Comparator.comparing(Notes::getCreatedd).reversed())
		        .collect(Collectors.toList());
	}
	
	public static List<Jokes> listByDateJokes(List<Jokes> jokes) {
		return   jokes.stream()
		        .sorted(Comparator.comparing(Jokes::getCreatedd).reversed())
		        .collect(Collectors.toList());
	}
	
	//without reverse
	public static List<Diary> listByDateDiary(List<Diary> diary) {
		return   diary.stream()
		        .sorted(Comparator.comparing(Diary::getDate))
		        .collect(Collectors.toList());
	}
	
	
}
