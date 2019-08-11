package com.web.blog.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;
import com.web.blog.model.LogIn;
import com.web.blog.model.AddNote;
import com.web.blog.model.editGoal;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.repositories.NoteRepository;
import com.web.blog.repositoriesImpl.GalleryGoalsImpl;
import com.web.blog.repositoriesImpl.NoteImpl;
import com.web.blog.util.PaginationNotes;
import com.web.blog.util.Sorts;
import com.web.blog.util.TimeClas;
import com.web.blog.entity.Notes;;


@Controller
public class crudNotesController {

	private static final Logger logger = LogManager.getLogger(crudNotesController.class);
	
	@Autowired
	NoteRepository noteRepository;
	
	@PutMapping(value = "/addnotes/{currentlyPage}")
    public String save(Model model, @Valid AddNote addnote, 
    		BindingResult bindingResult, @PathVariable String currentlyPage) throws IOException{
		
		if (bindingResult.hasErrors()) {
			
			
			int page = Integer.parseInt(currentlyPage);

			//get all notes
			List<Notes> notes = new ArrayList<>();
			noteRepository.findAll().forEach(notes::add);
			
			notes = TimeClas.setTimeagoNotes(notes);
			notes = Sorts.listByDate(notes);
			
			//pagination list;
			List<Integer> pagination = PaginationNotes.createPaginationList(notes);
			//....
			
			notes = PaginationNotes.displayPages(notes,String.valueOf(page));
			
			model.addAttribute("notes", notes);
			model.addAttribute("pagination", pagination);
			model.addAttribute("currentlyPage",page);
			model.addAttribute("login", new LogIn());
			model.addAttribute("addNote", addnote);
			model.addAttribute("weHaveErrors",true);
			
			
			return "notes";
        }

		//save to database
		NoteImpl.save(addnote, noteRepository);
		
		return "redirect:/notes/"+currentlyPage; 
    }
	
	@GetMapping(value = "/delete/{page}/{id}")
    public String delete(Model model, @PathVariable String page, @PathVariable String id) {
			
		NoteImpl.delete(UUID.fromString(id), noteRepository);	
		
		return "redirect:/notes/"+page;
    }
	
	@GetMapping(value = "/notes/{page}")
    public String read(Model model, @PathVariable String page) {
		
		page = PaginationNotes.isItNextButton(page);

		//get all notes
		List<Notes> notes = new ArrayList<>();
		noteRepository.findAll().forEach(notes::add);
		
		notes = TimeClas.setTimeagoNotes(notes);
		notes = Sorts.listByDate(notes);
		
		//pagination list;
		List<Integer> pagination = PaginationNotes.createPaginationList(notes);
		//....
		
		notes = PaginationNotes.displayPages(notes,page);
		
		model.addAttribute("notes", notes);
		model.addAttribute("pagination", pagination);
		model.addAttribute("currentlyPage", PaginationNotes.pageCantbeNegative(Integer.parseInt(page)));
		model.addAttribute("login", new LogIn());
		model.addAttribute("addNote", new AddNote());
		model.addAttribute("weHaveErrors",false);
		model.addAttribute("maxPage", PaginationNotes.getMaxValue(pagination));
		return "notes"; 
		
    }
	
}
