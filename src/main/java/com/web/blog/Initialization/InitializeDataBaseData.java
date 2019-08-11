package com.web.blog.Initialization;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.controllers.crudGoalsController;
import com.web.blog.entity.Jokes;
import com.web.blog.repositories.DiaryRepository;
import com.web.blog.repositories.GalleryRepository;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.repositories.JokesRepository;
import com.web.blog.repositories.NoteRepository;
import com.web.blog.service.UserService;

@Component
public class InitializeDataBaseData implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LogManager.getLogger(InitializeDataBaseData.class);

    @Autowired
    private JokesRepository jokeRepository;

	@Autowired
	DiaryRepository diaryrepository;
	
    @Autowired
    private UserService userService;
    
	@Autowired
	GoalsRepository goalsRepository;
	
	@Autowired
	GalleryRepository galleryRepository;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	@Autowired
	NoteRepository noteRepository;
	
		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			logger.info("Starting initializing data to database..loading...please, wait...");
			Initializator.initializeJokesTable(jokeRepository);
			Initializator.initializeDiaryOfTable(diaryrepository);
			Initializator.initializeUsers(passwordEncoder, userService);
      		Initializator.initializeGoalsEntity(goalsRepository);
      		Initializator.initializeGalleryEntity(galleryRepository);
			Initializator.initializeNoteEntity(noteRepository);
      		
      		
		}
	    
}


