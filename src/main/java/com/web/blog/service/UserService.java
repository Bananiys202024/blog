package com.web.blog.service;

import com.web.blog.entity.User;

public interface UserService {
    void save(User user);
    
    void generate_admin_password();
    
    User findByUsername(String username);
}
