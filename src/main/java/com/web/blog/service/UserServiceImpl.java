package com.web.blog.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.UDT.Role;
import com.web.blog.entity.User;
import com.web.blog.repositories.RoleRepository;
import com.web.blog.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    
    @Override
    public void save(User user) {
	
    	UUID id = UUIDs.timeBased();
    		
    	user.setPassword(passwordEncoder.encode(user.getPassword()));

    	com.web.blog.UDT.Role role = new com.web.blog.UDT.Role();
    	role.setId(id);
    	role.setName(user.getUsername());

    	List<com.web.blog.UDT.Role> list = new ArrayList();
    	list.add(role);
    	user.setId(id);
 
    	user.setRoles(new HashSet<>(list));
    	
    	com.web.blog.entity.Role ent = transformModelToEntity(role);
    	
    	roleRepository.save(ent);
    userRepository.save(user);
        
    }
    
    
    //we use it only 1 time;
    public void generate_admin_password() {
    	
//    	//You need point password here and then clear this field and comment method;
    	String password = "123";
    	String username = "Admin";
    	
    	UUID id = UUIDs.timeBased();
    	
    	com.web.blog.entity.Role roleAdmin = new com.web.blog.entity.Role(id,username,"ROLE_ADMIN",null);
    	
    	//UDT(type of tables in cassandra)
    	com.web.blog.UDT.Role role = new com.web.blog.UDT.Role();
    	role.setId(id);
    	role.setName(username);

    	List<com.web.blog.UDT.Role> UDTrole = new ArrayList();
    	UDTrole.add(role);
    	//...
    	
    	User userAdmin = new User(id, username, passwordEncoder.encode(password), null, new HashSet<>(UDTrole));

    	
   	roleRepository.save(roleAdmin);
    userRepository.save(userAdmin);
    }
   
private com.web.blog.entity.Role transformModelToEntity(Role role) {
		
		com.web.blog.entity.Role roleent = new com.web.blog.entity.Role(role.getId(), role.getName(), "ROLE_USER", null);

		return roleent;
	}

	@Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
