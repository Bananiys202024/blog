package com.web.blog.repositories;


import com.web.blog.entity.Gallery;
import com.web.blog.entity.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CassandraRepository<User, UUID> {
    
	@Query("SELECT * FROM user WHERE username=:username ALLOW FILTERING")
	User findByUsername(@Param("username") String username);

	Optional<User> findById(UUID fromString);
}
