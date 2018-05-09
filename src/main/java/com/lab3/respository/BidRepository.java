package com.lab3.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lab3.models.Bid;
import com.lab3.models.Project;

public interface BidRepository extends CrudRepository<Bid, Long> {
	
	@Query("SELECT t FROM Bid t where t.user_id = :id") 
    List<Bid> findBids(@Param("id") Integer id);
	
	@Query("SELECT t FROM Bid t where t.project_id = :id") 
    List<Bid> findProjectBids(@Param("id") Integer id);
	
	Bid findById(Integer id);
}