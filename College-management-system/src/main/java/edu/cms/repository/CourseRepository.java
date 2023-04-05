package edu.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.cms.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	
//method to find course by subject name
	List<Course>findBysubName(String name);

}
