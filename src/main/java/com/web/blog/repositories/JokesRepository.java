package com.web.blog.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.blog.dao.CustomizedJokes;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Jokes;

@Repository
public interface JokesRepository  extends CassandraRepository<Jokes, UUID>, CustomizedJokes<Jokes>{
	
	Optional<Jokes> findById(UUID id);
	
	void deleteById(UUID id);
	
    @Query("select jokes, createdd, deleted from jokes where count = :count ALLOW FILTERING")
	Jokes findRandomJoke(@Param("count") Long count);

    @Query("select MAX(count) from jokes")
	long askMaxCount();
    
    @Modifying
    @Query("update jokes set deleted=true where id=:id")
    void updateDeleteField(@Param("id") UUID id);
    
}
