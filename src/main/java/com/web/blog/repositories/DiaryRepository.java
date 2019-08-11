package com.web.blog.repositories;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.blog.dao.CustomizedDiary;
import com.web.blog.dao.CustomizedJokes;
import com.web.blog.entity.Diary;
import com.web.blog.entity.Gallery;
import com.web.blog.entity.Jokes;

public interface DiaryRepository extends CassandraRepository<Diary, UUID>, CustomizedDiary<Diary>{

	@Query("select count(*) from diary where date = :date ALLOW FILTERING")
	int existsByDate(@Param("date") Date date);
	
	Optional<Diary> findById(UUID fromString);
}

