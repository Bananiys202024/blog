package com.web.blog.Initialization;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.controllers.crudDiaryController;
import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Jokes;
import com.web.blog.entity.Notes;
import com.web.blog.entity.User;
import com.web.blog.model.AddValuesToDiary;
import com.web.blog.repositories.DiaryRepository;
import com.web.blog.repositories.GalleryRepository;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.repositories.JokesRepository;
import com.web.blog.repositories.NoteRepository;
import com.web.blog.repositoriesImpl.DiaryImpl;
import com.web.blog.service.UserService;

public class Initializator {

	private static final Logger logger = LogManager.getLogger(Initializator.class);

	
	public static void initializeJokesTable(JokesRepository jokeRepository) {
		
		List<Jokes> list = Generator.getListJokesEntity();
		
		for(Jokes entity:list)
		jokeRepository.save(entity);
	}

	public static void initializeDiaryOfTable(DiaryRepository diaryrepository) {
			DiaryImpl.generate(diaryrepository);
			DiaryImpl.update(Generator.getListDiaryEntity() ,diaryrepository);;
	}

	public static void initializeUsers(BCryptPasswordEncoder passwordEncoder, UserService userService) {

		//initialize admin
		userService.generate_admin_password();
		//initialize users
		
		List<User> users = Generator.getListUser(passwordEncoder);
		
		for(User user:users)
		userService.save(user);
	}

	public static void initializeGoalsEntity(GoalsRepository goalsRepository)  {
		
		try
		{
		goalsRepository.save(Generator.generateGoalsEntity());
		}
		catch(Exception e)
		{
			logger.error("Ops!Initializator!Ops!",e);
		}
	
		
	}

	public static void initializeGalleryEntity(GalleryRepository galleryRepository) {
		try
		{
			galleryRepository.save(Generator.generateGalleryEntity());
		}
		catch(Exception e)
		{
			logger.error("Ops!Initializator!Ops!",e);
		}
	}

	public static void initializeNoteEntity(NoteRepository noteRepository) {
		
		List<Notes> list = Generator.getListNotesEntity();
		
		for(Notes entity:list)
		noteRepository.save(entity);
		
	}


}
