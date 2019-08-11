package com.web.blog.repositoriesImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.controllers.crudDiaryController;
import com.web.blog.entity.Diary;
import com.web.blog.model.AddValuesToDiary;
import com.web.blog.repositories.DiaryRepository;
import com.web.blog.util.Financial360DayCalendar;
import com.web.blog.util.Sorts;

public class DiaryImpl {

	private static final Logger logger = LogManager.getLogger(DiaryImpl.class);

	
	public static void generate(DiaryRepository diaryrepository) {
		
		List<LocalDate> list = Financial360DayCalendar.getListDays();
		
		save(list,diaryrepository);

	}


	private static void save(List<LocalDate> list, DiaryRepository diaryrepository) {
		//generate
		
		for(LocalDate date :list) {
			
			if(checkIfNotExistInDataBase(date, diaryrepository))
			{
				Diary diary = new Diary(UUIDs.timeBased(), convertLocalDateToDate(date));
				diaryrepository.save(diary);
			}
			
		}
		
	}


	private static Date convertLocalDateToDate(LocalDate date) {
		return java.util.Date.from(date.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());

	}


	private static boolean checkIfNotExistInDataBase(LocalDate date, DiaryRepository diaryrepository) {
		
		int  result = diaryrepository.existsByDate(convertLocalDateToDate(date));
		
		if(result==0) {return true;}
		
		return false;
	}


	public static List<Diary> getDiary(DiaryRepository diaryrepository) {

		List<Diary> diary = new ArrayList<>();
		diaryrepository.findAll().forEach(diary::add);
				
		diary =  Sorts.listByDateDiary(diary);
		
		
		return diary;
	}


	public static void update(@Valid AddValuesToDiary addvaluestodiary, DiaryRepository diaryrepository) {
		
		Diary diary = new Diary();

		List<Diary> list = getDiary(diaryrepository);
		
		for(Diary lst:list)
		{
			DoYouShouldUpdateItThatsQuestion(addvaluestodiary, lst,diaryrepository );
		}
		
	}


	private static void DoYouShouldUpdateItThatsQuestion(AddValuesToDiary addvaluestodiary,Diary lst,  DiaryRepository diaryrepository) {
	
		try
		{
		diaryrepository.save(checkingvalueoffalse(addvaluestodiary,lst));	
		}
		catch(Exception e)
		{
			logger.info("Ops",e);
		}
		
		
	}

	//	this method for add values to diary table
	//	cheching if added values;
	
	private static Diary checkingvalueoffalse(AddValuesToDiary addvaluestodiary, Diary lst) {
	
		if(lst.isJava()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getJava()>0)
			{
			addvaluestodiary.setJava(addvaluestodiary.getJava()-1);
			lst.setJava(true);			
			}
		}
		
		if(lst.isElectro()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getElectro()>0)
			{
			addvaluestodiary.setElectro(addvaluestodiary.getElectro()-1);
			lst.setElectro(true);			
			}	
		}
		
		if(lst.isSport()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getSport()>0)
			{
			addvaluestodiary.setSport(addvaluestodiary.getSport()-1);
			lst.setSport(true);			
			}
		}
		
		if(lst.isUniversity()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getUniversity()>0)
			{
			addvaluestodiary.setUniversity(addvaluestodiary.getUniversity()-1);
			lst.setUniversity(true);			
			}		
		}
		
		if(lst.isEnglish()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getEnglish()>0)
			{
			addvaluestodiary.setEnglish(addvaluestodiary.getEnglish()-1);
			lst.setEnglish(true);			
			}
		}

		if(lst.isGerman()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getGerman()>0)
			{
			addvaluestodiary.setGerman(addvaluestodiary.getGerman()-1);
			lst.setGerman(true);			
			}
		}
		
		if(lst.isInterviewElectro()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getInterviewElectro()>0)
			{
			addvaluestodiary.setInterviewElectro(addvaluestodiary.getInterviewElectro()-1);
			lst.setInterviewElectro(true);			
			}
		}
		
		if(lst.isInterviewJava()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getInterviewJava()>0)
			{
			addvaluestodiary.setInterviewJava(addvaluestodiary.getInterviewJava()-1);
			lst.setInterviewJava(true);			
			}
		}
		
		if(lst.isXaXa()) 
		{	
		}
		else 
		{
			if(addvaluestodiary.getXaXa()>0)
			{
			addvaluestodiary.setXaXa(addvaluestodiary.getXaXa()-1);
			lst.setXaXa(true);			
			}
		}
		
		
		return lst;
	}

}
