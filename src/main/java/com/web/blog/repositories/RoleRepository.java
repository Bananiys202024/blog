package com.web.blog.repositories;


import com.web.blog.entity.Gallery;
import com.web.blog.entity.Role;
import com.web.blog.entity.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CassandraRepository<Role, UUID>{
	
	@Query("SELECT * FROM role WHERE name=:username ALLOW FILTERING")
	Role findByName(@Param("username") String username);

	Optional<Role> findById(UUID fromString);
}
