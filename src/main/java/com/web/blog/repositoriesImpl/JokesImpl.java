package com.web.blog.repositoriesImpl;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Jokes;
import com.web.blog.model.addNewGoal;
import com.web.blog.model.createJoke;
import com.web.blog.repositories.GoalsRepository;
import com.web.blog.repositories.JokesRepository;

public class JokesImpl {

	private static final Logger logger = LogManager.getLogger(JokesImpl.class);

	@PersistenceContext
	JokesRepository jokesrepository;	

	public static void save(createJoke createjoke, JokesRepository jokesRepository) {

		try 
		{			
		UUID id = UUIDs.timeBased();
			
		Jokes joke = new Jokes();
		joke.setCreatedd(new Date());
		joke.setDeleted(false);
		joke.setId(id);
		joke.setJokes(createjoke.getTextarea());
		joke.setCount(getMaxCountColumn(jokesRepository));
		
		jokesRepository.save(joke);
		
		logger.info("We added");
		}
		catch(Exception e)
		{
		logger.error("Ops!",e);
		}
		
	}


    public static Long getMaxCountColumn(JokesRepository jokesRepository) {
       
    	//get list of jokes
    	List<Jokes> jokes = new ArrayList<>();
		jokesRepository.findAll().forEach(jokes::add);
		//....
		
    	//get max value of column count;
		try 
		{
		Jokes maxBycount = jokes
			      		  .stream()
			      		  .max(Comparator.comparing(Jokes::getCount))
			              .orElseThrow(NoSuchElementException::new);
		
		return maxBycount.getCount()+1L;
		}
		catch(NoSuchElementException e) {
			return 0L+1L;
		}
		catch(Exception e) {
			logger.error("Ops!",e);
		}
		
    	return 0L;
    }


	public static Jokes askRandomJoke(JokesRepository jokesRepository, List<Jokes> joke) {
		
		if(joke.size()==0)
		{
		 return new Jokes(null,new Date(), "",false, 1L,"");	
		}
		
		boolean repeat = false;
		
		Jokes result = new Jokes();
		
		do
		{
		
		result = jokesRepository.findRandomJoke(getRandomCount(jokesRepository));

		if(result.isDeleted())
		{
		repeat=true;
		}
		else 
		{
		repeat=false;
		}
		
		}while(repeat);
	
		
			
		return result;
	}


	private static Long getRandomCount(JokesRepository jokesRepository) {
		
		 long leftLimit = 1L;
		 long rightLimit = jokesRepository.askMaxCount();
		 long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		
		 return generatedLong;
	}
	
	public static void delete(JokesRepository jokesRepository, String id)
	{
		try
		{
		jokesRepository.updateDeleteField(UUID.fromString(id));
		}
		catch(Exception e)
		{
			logger.error("Ops",e);
		}
		
	}

}
