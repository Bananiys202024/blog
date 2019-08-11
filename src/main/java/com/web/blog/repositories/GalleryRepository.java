package com.web.blog.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;

@Repository
public interface GalleryRepository  extends CassandraRepository<Gallery, UUID>{

	Optional<Gallery> findById(UUID fromString);

}
	