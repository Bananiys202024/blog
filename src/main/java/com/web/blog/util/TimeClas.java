package com.web.blog.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Jokes;
import com.web.blog.entity.Notes;
import com.web.blog.model.addNewGoal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.prettytime.PrettyTime;

public class TimeClas {

	private static final Logger logger = LogManager.getLogger(TimeClas.class);

	
	public static addNewGoal setTimeago(List<Goals> goals) {
		
		PrettyTime p = new PrettyTime();

	    for (Goals goal : goals) {

	    	goal.setTimeago(p.format(goal.getCreatedd()));
	    	goal.setCreatedd(goal.getCreatedd());
	    	goal.setDescription(goal.getDescription());
	    	goal.setEditedd(goal.getEditedd());
	    	goal.setId(goal.getId());
	    	goal.setImage(goal.getImage());
	    	goal.setTitle(goal.getTitle());
	    	
	    }
	    
	    addNewGoal addnewgoal = new addNewGoal();
	    addnewgoal.setAllList(goals);
	    
		return addnewgoal;
	}
	
public static List<Gallery> setTimeagoGallery(List<Gallery> gallery) {
		
		PrettyTime p = new PrettyTime();

	    for (Gallery goal : gallery) {

	    	goal.setTimeago(p.format(goal.getReachedd()));
	    	goal.setReachedd(goal.getReachedd());
	    	goal.setDescription(goal.getDescription());
	    	goal.setId(goal.getId());
	    	goal.setImage(goal.getImage());
	    	goal.setTitle(goal.getTitle());
	    }
	    

		return gallery;
	}


public static List<Notes> setTimeagoNotes(List<Notes> notes) {
	
	PrettyTime p = new PrettyTime();

    for (Notes note : notes) {

    	note.setCreatedd(note.getCreatedd());
    	note.setDelete(note.isDelete());
    	note.setId(note.getId());
    	note.setNote(note.getNote());
    	note.setTimeago(p.format(note.getCreatedd()));
    
    }

	return notes;
}

public static List<Jokes> setTimeagoJokes(List<Jokes> jokes) {
	
	PrettyTime p = new PrettyTime();

    for (Jokes joke : jokes) {

    	joke.setCreatedd(joke.getCreatedd());
    	joke.setDeleted(joke.isDeleted());
    	joke.setId(joke.getId());
    	joke.setJokes(joke.getJokes());
    	joke.setTimeago(p.format(joke.getCreatedd()));
    
    }

	return jokes;
}

}
