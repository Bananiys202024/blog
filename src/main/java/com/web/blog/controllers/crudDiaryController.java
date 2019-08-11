package com.web.blog.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.blog.model.AddValuesToDiary;
import com.web.blog.model.LogIn;
import com.web.blog.repositories.DiaryRepository;
import com.web.blog.repositoriesImpl.DiaryImpl;
import com.web.blog.util.ReflectionJava;
import com.web.blog.util.TimeClas;
import com.web.blog.entity.Diary;
import com.web.blog.entity.Jokes;


@Controller
public class crudDiaryController {

	private static final Logger logger = LogManager.getLogger(crudDiaryController.class);

	@Autowired
	DiaryRepository diaryrepository;
	
	@GetMapping(value = "/diary")
    public ModelAndView read() {
		
		Diary diary = new Diary();
		
		ModelAndView model = new ModelAndView("diary");
	    model.addObject("login", new LogIn());
		model.addObject("currentlyPage","diary");
	    model.addObject("NamesOfColumn",ReflectionJava.getNameOfColumn(diary));
	    model.addObject("Diary", DiaryImpl.getDiary(diaryrepository));
		model.addObject("AddValuesToDiary", new AddValuesToDiary());
	    
		return model; 
		
    }
	
	@GetMapping(value = "/generate")
    public ModelAndView Generate() {
			
		DiaryImpl.generate(diaryrepository);

		ModelAndView model = new ModelAndView("redirect:/diary");
		return model; 
		
    }
	
	@PutMapping(value = "/addvalues")
    public ModelAndView update(@Valid AddValuesToDiary addvaluestodiary) {
			
		DiaryImpl.update(addvaluestodiary ,diaryrepository);
		
		ModelAndView model = new ModelAndView("redirect:/diary");
		return model; 
		
    }
	
}
