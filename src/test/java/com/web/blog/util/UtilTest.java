package com.web.blog.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.blog.entity.Diary;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Jokes;
import com.web.blog.repository.DiaryRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UtilTest {

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DiaryRepositoryTest.class);

	    @Test
	    public void whenSizeMoreFour_thenAvailableOnRetrieval() {
	        assertEquals(booleanClass.checklimit(4), true); 
	        assertEquals(booleanClass.checklimit(5), true); 
	        assertEquals(booleanClass.checklimit(3), false); 
	    }
	  
	    @Test
	    public void CheckReturnType_thenAvailableOnRetrieval() {
  	
	    	List<Jokes> list = new ArrayList<Jokes>();
	    	Jokes joke = new Jokes();
	    	joke.setDeleted(true);
	    	list.add(joke);
	    	
	    	List<Jokes> lst = Exclude.exscludeDeletedJokes(list);
	    	
	    	assertEquals(lst.size(), 0); 
	    }
	  
	    @Test
	    public void whenPaginationJokes_thenAvailableOnRetrieval() {

	    	int numberOne = 1;
	    	int numberTwo = 4444;
	    	int numberThree = 0;
	    	int numberFive = -1;
	    	
	    	assertEquals(PaginationJokes.pageCantbeNegative(numberOne), numberOne); 
	    	assertEquals(PaginationJokes.pageCantbeNegative(numberTwo), numberTwo); 
	    	assertEquals(PaginationJokes.pageCantbeNegative(numberThree), 1); 
	    	assertEquals(PaginationJokes.pageCantbeNegative(numberFive), 1); 
		      
	    	assertEquals(PaginationJokes.ValueCantBeNegative(200,100), 100); 
	    	assertEquals(PaginationJokes.ValueCantBeNegative(-400,200), 0); 
	    	
	    	
	    	List<Integer> list = new ArrayList<Integer>() {{ 
	    	            add(512); 
	    	            add(581);
	    	            add(582);
	    	            add(-1000);
	    	            add(2);
	    	            add(0);
	    	            } }; 
	    	
	    	assertEquals(PaginationJokes.getMaxValue(list), 582); 

	    }
	    
	    @Test
	    public void whenPaginationNotes_thenAvailableOnRetrieval() {

	    	int numberOne = 1;
	    	int numberTwo = 4444;
	    	int numberThree = 0;
	    	int numberFive = -1;
	    	
	    	assertEquals(PaginationNotes.pageCantbeNegative(numberOne), numberOne); 
	    	assertEquals(PaginationNotes.pageCantbeNegative(numberTwo), numberTwo); 
	    	assertEquals(PaginationNotes.pageCantbeNegative(numberThree), 1); 
	    	assertEquals(PaginationNotes.pageCantbeNegative(numberFive), 1); 
		      
	    	assertEquals(PaginationNotes.ValueCantBeNegative(200,100), 100); 
	    	assertEquals(PaginationNotes.ValueCantBeNegative(-400,200), 0); 
	    	
	    	
	    	List<Integer> list = new ArrayList<Integer>() {{ 
	    	            add(512); 
	    	            add(581);
	    	            add(582);
	    	            add(-1000);
	    	            add(2);
	    	            add(0);
	    	            } }; 
	    	
	    	assertEquals(PaginationNotes.getMaxValue(list), 582); 

	    }

}
