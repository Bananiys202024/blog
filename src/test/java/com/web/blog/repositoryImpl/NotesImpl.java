package com.web.blog.repositoryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Diary;
import com.web.blog.entity.Notes;
import com.web.blog.model.AddNote;
import com.web.blog.repositories.NoteRepository;
import com.web.blog.repositoriesImpl.NoteImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NotesImpl {

		@Autowired
		NoteRepository noteRepository;
	
		final UUID id = UUIDs.timeBased();
		
		final AddNote addnote = new AddNote(id, null, null, false);
		
	    @Test
	    public void Saving_thenAvailableOnRetrievalEntityJokes() {
		 	
	    	AddNote addnote = new AddNote(id, null, null, false);
	    	
		 	NoteImpl.save(addnote, noteRepository);
		 
		 	Optional<Notes> note = noteRepository.findById(id);
		 	Notes found = note.orElse(new Notes());
		 	
	        assertEquals(addnote.getId(), found.getId()); 
	        
	    }
	    
	    @Test
	    public void Deleting_thenAvailableOnRetrievalEntityJokes() {
		 	
	    	 NoteImpl.delete(id, noteRepository);
	
	         Optional<Notes> noteOpt = noteRepository.findById(id);
	 	 	 Notes foundAfterDelete = noteOpt.orElse(new Notes());
	 	 	
	         assertNotEquals(addnote.getId(), foundAfterDelete.getId()); 
	        
	    }

       
	    
}
