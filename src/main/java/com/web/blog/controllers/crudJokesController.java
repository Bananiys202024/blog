package com.web.blog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.blog.entity.Jokes;
import com.web.blog.entity.Notes;
import com.web.blog.model.LogIn;
import com.web.blog.model.createJoke;
import com.web.blog.repositories.JokesRepository;
import com.web.blog.repositoriesImpl.JokesImpl;
import com.web.blog.util.Exclude;
import com.web.blog.util.PaginationJokes;
import com.web.blog.util.PaginationNotes;
import com.web.blog.util.Sorts;
import com.web.blog.util.TimeClas;

import ch.qos.logback.classic.Logger;
import org.apache.logging.log4j.LogManager;

@Controller
public class crudJokesController {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(crudJokesController.class);
	
	@Autowired
	private JokesRepository jokesRepository;
	
	@GetMapping(value = {"/jokes/{page}", "/"})
    public ModelAndView read(@PathVariable(required = false) String page) {
		
		page = PaginationNotes.isItNextButton(page);
					
		//get all notes
		List<Jokes> jokes = new ArrayList<>();
		jokesRepository.findAll().forEach(jokes::add);
		
		//...
		//process with jokes
		jokes =  Exclude.exscludeDeletedJokes(jokes);
		jokes = TimeClas.setTimeagoJokes(jokes);
		jokes = Sorts.listByDateJokes(jokes);
		//....
		
		//pagination list;
		List<Integer> pagination = PaginationJokes.createPaginationList(jokes);
		//....
		
		jokes = PaginationJokes.displayPages(jokes,page);

		//random jokes
		Jokes randomjoke = JokesImpl.askRandomJoke(jokesRepository,jokes);
		//...
		
		ModelAndView model = new ModelAndView("jokes");
	    model.addObject("login", new LogIn());
		model.addObject("pagination", pagination);
		model.addObject("maxPage", PaginationNotes.getMaxValue(pagination));
		model.addObject("currentlyPage", PaginationNotes.pageCantbeNegative(Integer.parseInt(page)));
		model.addObject("createJoke", new createJoke());
		model.addObject("jokes",jokes);
		model.addObject("randomjoke", randomjoke);
		
		return model; 
    }

	@PutMapping(value = "/createJoke/{page}")
    public ModelAndView create(@PathVariable String page, @Valid createJoke createjoke, BindingResult bindingResult) {

			if (bindingResult.hasErrors()) {
			
			page = PaginationNotes.isItNextButton(page);

			//get all notes
			List<Jokes> jokes = new ArrayList<>();
			jokesRepository.findAll().forEach(jokes::add);
			//...
			
			jokes =  Exclude.exscludeDeletedJokes(jokes);
			jokes = TimeClas.setTimeagoJokes(jokes);
			jokes = Sorts.listByDateJokes(jokes);
			
			//pagination list;
			List<Integer> pagination = PaginationJokes.createPaginationList(jokes);
			//....
			
			jokes = PaginationJokes.displayPages(jokes,page);
			
			//random jokes
			Jokes randomjoke = JokesImpl.askRandomJoke(jokesRepository,jokes);
			//...
			
			ModelAndView model = new ModelAndView("jokes");
		    model.addObject("login", new LogIn());
			model.addObject("pagination", pagination);
			model.addObject("maxPage", PaginationNotes.getMaxValue(pagination));
			model.addObject("currentlyPage", PaginationNotes.pageCantbeNegative(Integer.parseInt(page)));
			model.addObject("createJoke", createjoke);
			model.addObject("jokes",jokes);
			model.addObject("randomjoke", randomjoke);
			
			return model;
        }
		
		//save to databse
		JokesImpl.save(createjoke, jokesRepository);

		return new ModelAndView("redirect:/jokes/"+page);
    }
	
	
	@DeleteMapping("/jokeDelete/{page}/{id}")
    public ModelAndView delete(@PathVariable String page, @PathVariable String id) {
		JokesImpl.delete(jokesRepository, id);
		return new ModelAndView("redirect:/jokes/"+page); 
    }

	
}
