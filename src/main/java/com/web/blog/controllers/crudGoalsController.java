package com.web.blog.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;
import com.web.blog.model.LogIn;
import com.web.blog.model.addNewGoal;
import com.web.blog.model.editGoal;
import com.web.blog.repositories.GalleryRepository;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.repositoriesImpl.GalleryGoalsImpl;
import com.web.blog.repositoriesImpl.GoalsImpl;
import com.web.blog.util.TimeClas;
import com.web.blog.util.booleanClass;

import org.apache.commons.io.IOUtils;

@Controller
public class crudGoalsController {

	private static final Logger logger = LogManager.getLogger(crudGoalsController.class);
	
	@Autowired
	GoalsRepository goalsRepository;
	
	@Autowired
	GalleryRepository galleryRepository;
	
	@PutMapping(value = "/savegoal")
    public String create(Model model, @Valid addNewGoal newgoal, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("login", new LogIn());
			model.addAttribute("currentlyPage","goals");
			model.addAttribute("addNewGoal", newgoal);
			
			//get all goals
			List<Goals> goals = new ArrayList<>();
			goalsRepository.findAll().forEach(goals::add);
			
			//get all gallery goals
			List<Gallery> gallery = new ArrayList<>();
			galleryRepository.findAll().forEach(gallery::add);
			
			model.addAttribute("goals", TimeClas.setTimeago(goals)); 
			model.addAttribute("gallery", TimeClas.setTimeagoGallery(gallery));
			model.addAttribute("limit",booleanClass.checklimit(goals.size()));
			
			return "goals";
        }

		
		//save to database;
		GoalsImpl.save(newgoal,goalsRepository);

		return "redirect:/goals"; 
    }
	
	
	@GetMapping(value = "/goals")
    public String read(Model model) {
		
	    model.addAttribute("login", new LogIn()); //it for bottom menu
		model.addAttribute("currentlyPage","goals"); // it for top menu
		model.addAttribute("addNewGoal", new addNewGoal()); // it for another page
		
		//get all goals
		List<Goals> goals = new ArrayList<>();
		goalsRepository.findAll().forEach(goals::add);
		
		//get all gallery goals
		List<Gallery> gallery = new ArrayList<>();
		galleryRepository.findAll().forEach(gallery::add);
		
		model.addAttribute("goals", TimeClas.setTimeago(goals)); 
		model.addAttribute("gallery", TimeClas.setTimeagoGallery(gallery));
		model.addAttribute("editGoal",new editGoal());
		model.addAttribute("limit",booleanClass.checklimit(goals.size()));
		
		return "goals";
    }

	@PutMapping(value = "/editgoal")
    public String edit(Model model, @Valid editGoal editgoal, BindingResult bindingResult) throws IOException{

		if (bindingResult.hasErrors()) {
			//initialize page
		 	model.addAttribute("login", new LogIn()); //it for bottom menu
			model.addAttribute("currentlyPage","goals"); // it for top menu
			model.addAttribute("addNewGoal", new addNewGoal()); // it for another page
			
			//get all goals
			List<Goals> goals = new ArrayList<>();
			goalsRepository.findAll().forEach(goals::add);
			
			//get all gallery goals
			List<Gallery> gallery = new ArrayList<>();
			galleryRepository.findAll().forEach(gallery::add);
			
			model.addAttribute("goals", TimeClas.setTimeago(goals)); 
			model.addAttribute("gallery", TimeClas.setTimeagoGallery(gallery));
			model.addAttribute("editGoal", editgoal);
			model.addAttribute("backendErrors",editgoal.getId());

			return "goals";
	}
	
	//save to database;
	GoalsImpl.update(editgoal,goalsRepository);

	return "redirect:/goals"; 
    }
	
	@PutMapping(value = "/addgallery")
    public String saveg(addNewGoal newgoal) throws IOException{
		GalleryGoalsImpl.save(UUID.fromString(newgoal.getId()),goalsRepository,galleryRepository);		
		return "redirect:/goals"; 
    }
	
	//display image
	@GetMapping(value = "/imageGoal/{id:.+}")
	public @ResponseBody ResponseEntity<byte[]>  showProductImageGoal(@PathVariable String id,
			HttpServletResponse response) throws IOException{

	 Optional<Goals> goalsOpt = goalsRepository.findById(UUID.fromString(id));	 
	 Goals goals = goalsOpt.orElse(new Goals());
	 
	 ByteBuffer buffer = goals.getImage();
	 byte[] bytes = buffer.array();
	 
	 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

	}
	
	
	//display image
	@GetMapping(value = "/imageGallery/{id:.+}")
	public @ResponseBody ResponseEntity<byte[]>  showProductImageGallery(@PathVariable String id,
			HttpServletResponse response) throws IOException{

	 Optional<Goals> goals = goalsRepository.findById(UUID.fromString(id));	 
	 	
	 Optional<Gallery> galleryOpt = galleryRepository.findById(UUID.fromString(id));
	 Gallery gallery = galleryOpt.orElse(new Gallery());
	 
	 ByteBuffer buffer = gallery.getImage();
	 logger.info("that---"+buffer);
	 
	 byte[] bytes = buffer==null?new byte[0]:buffer.array();
	 
	 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		 
	}
	
	
	
}
