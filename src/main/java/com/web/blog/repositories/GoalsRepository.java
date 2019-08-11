package com.web.blog.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.web.blog.entity.Goals;

@Repository
public interface GoalsRepository  extends CassandraRepository<Goals, UUID>{
	Optional<Goals> findById(UUID id);
}
	