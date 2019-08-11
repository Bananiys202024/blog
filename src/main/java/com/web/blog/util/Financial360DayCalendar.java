package com.web.blog.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.blog.repositoriesImpl.DiaryImpl;


public class Financial360DayCalendar {

	private static final Logger logger = LogManager.getLogger(Financial360DayCalendar.class);
	
	public static List<LocalDate> getListDays() {
		
		int days=31; //how do many days generate?

		return IntStream
				.rangeClosed(1, days)
				.boxed().map(c -> LocalDate.now().plusDays((Integer) c))
				.collect(Collectors.toList());
	}	
}
