package edu.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;
import javax.validation.Valid;

@RestController
@RequestMapping("/course")//mapping to course
public class CourseController {
	
	@Autowired//injected courseservice here
	private CourseService courseService;

	
	@Autowired//injected courseconverter here
	private CourseConverter converter;
	
	
	//mapping to create course
	@PostMapping("/createCourse")
	public String createCourse(@RequestBody Course course)
	{
		return courseService.createCourse(course);
	}
	
	
	//mapping to get course by particular id
	@GetMapping("/getCourseById/{id}")
	public CourseDTO getCourseById(@PathVariable("id")int id)
	{
		return courseService.getCourseById(id);
	}
	
	
	
	//mapping to get all courses details
	@GetMapping("/getAllCourses")
	public List<CourseDTO> getAllCourseDetails()
	{
		return courseService.getAllCourseDetails();
	}

	
	
	//mapping to update course by particular id
	@PutMapping("/updateCourse/{id}")
	public CourseDTO updateCourseDetails(@Valid @PathVariable("id") int id,
			@RequestBody CourseDTO courseDTO)
	{
		Course course= converter.convertDTOToCourseEntity(courseDTO);
		return courseService.updateCourseDetails(id, course);
	}
	
	//mapping to delete course by particular id
	@DeleteMapping("/deleteCourseById/{id}")
	public String deleteCourseById(@PathVariable("id") int id)
	{
		return courseService.deleteCourseById(id);
	}
	
	//mapping to delete all courses details
	@DeleteMapping("/deleteAllCourses")
	public ResponseEntity<String> deleteAllCourses()
	{
		courseService.deleteAllCourseDetails();
		return new ResponseEntity<String>("All courses details have been deleted successfully!", HttpStatus.OK);
	}
	
	//mapping to get course by particular name
	@GetMapping("/getCourseBysubName/{name}")
	public List<CourseDTO> getCourseBysubName(@PathVariable("name") String name)
	{
		return courseService.findBysubtName(name);
	}
	
	//mapping to assign teacher to particular course
	@PostMapping("/assignTeacherToCourse/{teachId}/{courseId}")
	public CourseDTO assignTeacherToCourse(@PathVariable ("teachId")int teacherId,
			@PathVariable ("courseId")int courseId) {
		return courseService.assignTeacherToCourse(teacherId, courseId);
	}
	
}

