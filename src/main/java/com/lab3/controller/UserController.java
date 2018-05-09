package com.lab3.controller;


import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.lab3.models.*;
import com.lab3.respository.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {    
	
	    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BidRepository bidRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @RequestMapping(value = "/v1/api/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  User addUser(@Valid User user) {
    	
    	user.setSkills("");
    	user.setAbout("");
    	user.setAvatar("");
    	user.setPhone("");
    	
    	userRepository.save(user);
    	
        return user;
        
    } 
    
    @RequestMapping(value = "/v1/api/user/update/{id}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  User updateUser(@PathVariable(value = "id") Integer id,@Valid User userdata) {
    	
    	User user = userRepository.findById(id);
    	    	
    	user.setAbout(userdata.getAbout());
    	user.setAvatar("");
    	user.setEmail(userdata.getEmail());
    	user.setFirst_name(userdata.getFirst_name());
    	user.setLast_name(userdata.getLast_name());
    	user.setPhone(userdata.getPhone());
    	user.setSkills(userdata.getSkills());
    	user.setType(userdata.getType());
    	
    	userRepository.save(user);
    	
        return user;
        
    }
    
    //Map<String,Object>
    
    @RequestMapping(value = "/v1/api/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Object login(
    		@RequestParam(name="email", required=false, defaultValue="") String email,
    		@RequestParam(name="password", required=false, defaultValue="") String password
    		, HttpServletRequest request) {
    	  
    	Map<String,Object> res = new HashMap<>();
        Object ret = null;
    	User user = userRepository.findByEmail(email);
    	
    	
    	
    	if( user.getPassword().equals(password) ) {
    		res.put("success",true);
    		ret = user;
    		
    		request.getSession().setAttribute("logged",true);
    		request.getSession().setAttribute("user",user.getId());
    		
    		System.out.println("login successfull");
    		 
    	} else {
    		
    		System.out.println("login failed");
    		
    		res.put("success",false);
    		ret = res;
    	}
    	
        return ret;
    }
    
    
    @RequestMapping(value = "/v1/api/session", method = RequestMethod.GET,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
    public  Object session(HttpSession session) {
    	  
    	Map<String,Object> res = new HashMap<>();
    	
    	if(session.getAttribute("user")!= null) {
    		
    		User user = userRepository.findById((Integer)session.getAttribute("user"));
    		
    		res.put("logged",true);
    		res.put("user",user);
    		
    	} else {
    		
    		res.put("logged",false);
    		
    	}
    	   	 
    	
        return res;
    }
    
    @RequestMapping(value = "/v1/api/logout", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Object logout(HttpSession session) {
    	  
    	Map<String,Object> res = new HashMap<>();
    	
    	session.removeAttribute("user");
    	res.put("logged",false);
    		
    	
        return res;
    }
    
    
    @RequestMapping(value = "/v1/api/userdetails/{id}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Object userdetails(@PathVariable(value = "id") Integer id,HttpSession session) {
    	
    	User user = null;
    			
    		 user = userRepository.findById(id);
    	
        return user;
    }
    
   

}    
      