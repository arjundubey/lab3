package com.lab3.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProjectController {
	
	@Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BidRepository bidRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @RequestMapping(value = "/v1/api/project", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Project createProject(@Valid Project project,HttpSession session) {
        
    	project.setSlug( project.getName().replace(' ','-').toLowerCase() );
    	    	
    	project.setWin_bid_id(0);
    	project.setStatus("OPEN");
    	project.setBid_winner(0);
    	
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	
    	Timestamp timestamp = new Timestamp(now.getTime());

    	
    	project.setCreated_at(timestamp);
    	project.setUpdated_at(timestamp);
    	
    	projectRepository.save(project);
    	
    	return project;
        
    }
    
    @GetMapping(path="/v1/api/projects")
   	public @ResponseBody Iterable<Project> getAllProjects() {
   		// This returns a JSON or XML with the users
   		return projectRepository.findAll();
   	} 
    
    @PostMapping(path="/v1/api/myprojects")
   	public @ResponseBody List<Project> getMyProjects(@RequestParam(name="user_id", required=false, defaultValue="") Integer user_id, HttpSession session) {
   		
    	        
        List<Project> projects = projectRepository.findMyProjects((Integer)user_id);
    	
   		return projects;
   	}
    
    @GetMapping(path="/v1/api/dashprojects")
   	public @ResponseBody List<Project> dashprojects(HttpSession session) {
   		
    	        
        List<Project> projects = projectRepository.findMyProjects((Integer)session.getAttribute("user"));
    	List<Project> nb = new ArrayList<Project>();
        
        for (int i = 0; i < projects.size(); i++) {
            Project p = projects.get(i);
            
        	User u = userRepository.findById(p.getBid_winner());
        	p.setBid_winner_details(u);
        	
        	Bid b = bidRepository.findById(p.getWin_bid_id());
        	p.setWin_bid_details(b);
        	
        	nb.add(p);
        }
   		return nb;
   	}
    
    
    @RequestMapping(value = "/v1/api/project/{slug}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map getProject(@PathVariable(value = "slug") String slug) {
    	
    	Project project = projectRepository.findBySlug(slug);
    	
    	HashMap<String,Object> res = new HashMap<>();
    	List<Bid> bids= bidRepository.findProjectBids(project.getId());
    	List<Bid> nb = new ArrayList<Bid>();
    	res.put("project",project);
    	
    	for (int i = 0; i < bids.size(); i++) {
            Bid b = bids.get(i);
            
            User u = userRepository.findById(b.getUser_id());
        	b.setUserDetails(u);
        	
        	nb.add(b);
        }
    	
    	res.put("bids",nb);
    	
        return res;
        
    }
    
    @RequestMapping(value = "/v1/api/project/{slug}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Project update(@PathVariable(value = "slug") String slug,@Valid Project projectData) {
    	
    	Project project = projectRepository.findBySlug(slug);
    	
    	project.setName(projectData.getName());
    	project.setSlug( projectData.getName().replace(' ','-').toLowerCase() );
    	project.setStatus(projectData.getStatus());
    	project.setDescription(projectData.getDescription());
    	project.setSkills(projectData.getSkills());
    	
    	project.setMax_budget(projectData.getMax_budget());
    	project.setMin_budget(projectData.getMin_budget());
    	project.setFile(projectData.getFile());
    	
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	Timestamp timestamp = new Timestamp(now.getTime());
    	project.setUpdated_at(timestamp);
    	
    	projectRepository.save(project);
    	
        return project;
        
    }
    
    
}
