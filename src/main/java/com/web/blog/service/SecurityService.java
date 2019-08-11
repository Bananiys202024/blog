package com.web.blog.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
