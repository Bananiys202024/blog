package com.web.blog.repositoriesImpl;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Goals;
import com.web.blog.model.addNewGoal;
import com.web.blog.repositories.GalleryRepository;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.util.Convertor;

public class GalleryGoalsImpl {

	private static final Logger logger = LogManager.getLogger(GalleryGoalsImpl.class);
	
	public static void save(UUID id, GoalsRepository goalsRepository, GalleryRepository galleryrepository) {

		try 
		{			

		Optional<Goals> goalsOpt = goalsRepository.findById(id);
		Goals goals = goalsOpt.orElse(new Goals());
		
		galleryrepository.save(Convertor.convertGoalsToGalleryClass(goals));
		goalsRepository.delete(goals);
		logger.info("We added");
		}
		catch(Exception e)
		{
		logger.error("Ops!",e);
		}
		
	}
	
}
