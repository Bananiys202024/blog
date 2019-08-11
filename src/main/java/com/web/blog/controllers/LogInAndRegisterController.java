package com.web.blog.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.UDT.Role;
import com.web.blog.entity.User;
import com.web.blog.model.LogIn;
import com.web.blog.repositories.UserRepository;
import com.web.blog.service.SecurityService;
import com.web.blog.service.UserService;

@Controller
public class LogInAndRegisterController {

    private static final Logger logger = LogManager.getLogger(LogInAndRegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/login")
	public ModelAndView CanISignUp() {
		return new ModelAndView("/");
	}
    
    @GetMapping("/registration")
    public ModelAndView registration() {
    	
    	ModelAndView model = new ModelAndView("LogInAndRegister/LogIn/singUp");
    	
    	model.addObject("userForm", new User());
        model.addObject("login", new LogIn());
        
		return model;
    }
    
	@GetMapping("/signUp")
	public ModelAndView readUp() {
		return new ModelAndView("LogInAndRegister/LogIn/signUp","login", new LogIn());
	}
	
	 @PostMapping("/registration")
	    public String registration(@ModelAttribute("userForm") User userForm) {

	        userService.save(userForm);
//			auto-login not work because i not implemented authonticationManager;
//	        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

	        return "redirect:/login";
	    }
	
	
	@GetMapping("/login")
	public ModelAndView readIn() {
		return new ModelAndView("LogInAndRegister/LogIn/login","login", new LogIn());
	}
	
	
	public Object getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getPrincipal();
	}
	
	
	
}
