package com.web.blog.repositoriesImpl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.utils.Bytes;
import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Goals;
import com.web.blog.model.addNewGoal;
import com.web.blog.model.editGoal;
import com.web.blog.repositories.GoalsRepository;

import javax.sql.rowset.serial.SerialBlob;

@Repository
public class GoalsImpl {

	private static final Logger logger = LogManager.getLogger(GoalsImpl.class);
	
	public static void save(addNewGoal newgoal, GoalsRepository goalsRepository) {

		try 
		{			
			
		ByteBuffer buf = ByteBuffer.wrap(newgoal.getImage().getBytes());

		Goals goals= new Goals();
		goals.setDescription(newgoal.getDescription());
		goals.setTitle(newgoal.getGoal());
		goals.setId(UUIDs.timeBased());
		goals.setImage(buf);
		goals.setCreatedd(new Date());
		goals.setEditedd(null);
		buf.clear();
		
		goalsRepository.save(goals);
		logger.info("We added");
		}
		catch(Exception e)
		{
		logger.error("Ops!",e);
		}
		
	}

	public static void update(@Valid editGoal newgoal, GoalsRepository goalsRepository) {
		
		try 
		{

		ByteBuffer buf = null;	
			
		boolean emptyimage = newgoal.getImageT().isEmpty();	
		logger.info("Check--"+emptyimage);
		if(!emptyimage) 
		{
		buf = ByteBuffer.wrap(newgoal.getImageT().getBytes());
		}
		
		Goals goals= new Goals();
		goals.setDescription(newgoal.getDescription());
		goals.setTitle(newgoal.getGoal());
		goals.setId(UUID.fromString(newgoal.getId()));
		
		if(emptyimage) 
		{
		Optional<Goals> goalOpt = goalsRepository.findById(UUID.fromString(newgoal.getId()));		
		Goals goal = goalOpt.orElse(new Goals());
		
		goals.setImage(goal.getImage());
		}
		else 
		{
			goals.setImage(buf);	
			buf.clear();
		}
		
		goals.setCreatedd(newgoal.getCreatedd());
		goals.setEditedd(new Date());

		//find by id product
		//get image
		goalsRepository.save(goals);
		logger.info("Successfully updated");
		}
		catch(Exception e)
		{
		logger.error("Ops!",e);
		}
		
	}

}
