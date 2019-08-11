package com.web.blog.repositoriesImpl;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Notes;
import com.web.blog.model.AddNote;
import com.web.blog.model.addNewGoal;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.repositories.NoteRepository;


@Repository
public class NoteImpl {

	private static final Logger logger = LogManager.getLogger(NoteImpl.class);
	
	public static void save(AddNote addnote, NoteRepository noteRepository) {

		try 
		{			
		UUID id= UUIDs.timeBased();
			
		if(addnote.getId()!=null)
		{
			id = addnote.getId();
		}
			
		Notes add = new Notes();
		add.setCreatedd(new Date());
		add.setDelete(false);
		add.setId(id);
		add.setNote(addnote.getNote());
		
		noteRepository.save(add);
		logger.info("We added");
		}
		catch(Exception e)
		{
		logger.error("Ops!",e);
		}
		
	}
	
	public static void delete(UUID id, NoteRepository noteRepository) {
		
		try 
		{
		Optional<Notes> noteOpt = noteRepository.findById(id);
		Notes note = noteOpt.orElse(new Notes());	
				
		noteRepository.delete(note);
		}
		catch(Exception e)
		{
			logger.error("Ops!",e);
		}
		
	}
	
}
