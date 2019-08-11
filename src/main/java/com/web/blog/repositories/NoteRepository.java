package com.web.blog.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.web.blog.entity.Notes;


@Repository
public interface NoteRepository  extends CassandraRepository<Notes, UUID>{
	Optional<Notes> findById(UUID id);
	
	
}
