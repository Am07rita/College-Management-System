package edu.cms.service;

import java.util.List;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;


public interface CourseService {

	String createCourse(Course course);   //method to create course
	 CourseDTO getCourseById(int id);   //method to get course by id
	 List<CourseDTO> getAllCourseDetails();	//method to get all course details
     CourseDTO updateCourseDetails(int id, Course course);	//method to update course details
 	 String deleteCourseById(int id);	//method to delete course by id
 	 void deleteAllCourseDetails();		//method to delete all course
 	List<CourseDTO> findBysubtName(String name);	//method to find course thorugh subjectname
 	CourseDTO assignTeacherToCourse(int teacherId,int courseId);	//method to assign teacher to particular course
}
