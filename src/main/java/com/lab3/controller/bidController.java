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
public class bidController {
	
	@Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BidRepository bidRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @RequestMapping(value = "/v1/api/bid", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Bid createProject(@Valid Bid bid,HttpSession session) {
        
    	
    	//bid.setUser_id((Integer)session.getAttribute("user"));
    	
    	
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	
    	Timestamp timestamp = new Timestamp(now.getTime());

    	
    	bid.setCreated_at(timestamp);
    	bid.setUpdated_at(timestamp);
    	
    	bidRepository.save(bid);
    	
    	return bid;
        
    }
    
    @PostMapping(path="/v1/api/mybids")
   	public @ResponseBody List<Bid> getMyProjects(HttpSession session,
   			@RequestParam(name="user_id", required=false, defaultValue="") Integer user_id
   			) {
   		
    	        
        List<Bid> bids = bidRepository.findBids((Integer)user_id);
        List<Bid> nb = new ArrayList<Bid>();
        
        for (int i = 0; i < bids.size(); i++) {
            Bid b = bids.get(i);
            
        	User u = userRepository.findById(b.getUser_id());
        	b.setUserDetails(u);
        	
        	Project p = projectRepository.findById(b.getProject_id());
        	b.setProjectDetails(p);
        	//System.out.println("Name =====> "+u.getFirst_name());
        	
        	nb.add(b);
        }
    	
   		return nb;
   	}
    
    @RequestMapping(value = "/v1/api/finalbid", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public  Project update(
    		@RequestParam(name="bid_winner", required=false, defaultValue="") Integer bid_winner,
    		@RequestParam(name="win_bid_id", required=false, defaultValue="") Integer win_bid_id,
    		@RequestParam(name="projectid", required=false, defaultValue="") Integer projectid
    		) {
    	
    	
    	Project project = projectRepository.findById(projectid);
    	
    	
    	project.setBid_winner(bid_winner);
    	project.setWin_bid_id(win_bid_id);
    	project.setStatus("CLOSED");    	
    	
    	projectRepository.save(project);
    	
        return project;
        
    }
    
}
