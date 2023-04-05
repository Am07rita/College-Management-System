package edu.cms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;


@Component
public class CourseConverter {

	//method to convert  CourseDTO to CourseEntity 
	public Course convertDTOToCourseEntity(CourseDTO courseDTO)
	{
		Course course=new Course();
		if(course!=null)
		{
			
			BeanUtils.copyProperties(courseDTO, course);
		}
		return course;
	}
	
	
	//method to convert  CourseEntity to CourseDTO 
	public CourseDTO convertCourseEntityToDTO(Course course) {
		
		CourseDTO courseDTO=new CourseDTO();
		if(course!=null)
		{
			BeanUtils.copyProperties(course, courseDTO);
		}
		return courseDTO;
	}
}
