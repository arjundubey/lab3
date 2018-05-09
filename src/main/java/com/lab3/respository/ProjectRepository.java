package com.lab3.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lab3.models.Project;
import com.lab3.models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findBySlug(String slug);
	Project findById(Integer id);
	
	@Query("SELECT t FROM Project t where t.user_id = :id") 
    List<Project> findMyProjects(@Param("id") Integer id);
	
}
