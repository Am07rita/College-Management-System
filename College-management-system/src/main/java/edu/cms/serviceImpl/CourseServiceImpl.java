package edu.cms.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.cms.entity.Course;
import edu.cms.entity.Teacher;
import edu.cms.exception.ResourceNotFoundException;
import edu.cms.model.CourseDTO;
import edu.cms.repository.CourseRepository;
import edu.cms.repository.TeacherRepository;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

	
	//logger statically create
	private static final Logger log=LoggerFactory.getLogger(Course.class);
	
	@Autowired
	private CourseRepository courserepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseConverter converter;
	
	
	//method to create course
	@Override
	public String createCourse(Course course) {
		String msg=null;
	
	courserepository.save(course);
	log.info("Course "+course.toString()+" added at "+new Date());
	if(course!=null)
	{
		msg="Course details saved successfully!!";
	}
		return msg;
	}

	
	//method to get course by id
	@Override
	public CourseDTO getCourseById(int id) {
		Course course = courserepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Course", "Id", id));
		log.info("Course details get with id "+id+" at"+new Date());
		return converter.convertCourseEntityToDTO(course);
		
	}

	
	//method to get all course details
	@Override
	public List<CourseDTO> getAllCourseDetails() {
		List<Course> courses=courserepository.findAll();
		List<CourseDTO> course1DTO = new ArrayList<>();
		log.info("All Courses details found  at"+new Date());
		for(Course c:courses)
		{

			course1DTO.add(converter.convertCourseEntityToDTO(c));
		}
		return course1DTO;
	}

	
	//method to update course details
	@Override
	public CourseDTO updateCourseDetails(int id, Course course) {
		Course existingC=courserepository.findById(id).get();
		
		existingC.setSubId(course.getSubId());
		existingC.setSubName(course.getSubName());
		existingC.setPrice(course.getPrice());
		
		courserepository.save(existingC);
		log.info("Course details updated with id "+id+"  at "+new Date());
		return converter.convertCourseEntityToDTO(existingC);
	}

	//method to delete course by id
	@Override
	public String deleteCourseById(int id) {
		String msg = null;
		Optional<Course> course=courserepository.findById(id);
		if(course.isPresent()) 
		{
			courserepository.deleteById(id);
			log.info("Course details deleted with id "+id+"  at "+new Date());
			msg="Course with id "+id+" has been Deleted Successfully!!"; 
		}
		else
		{
			throw new ResourceNotFoundException("Course","Id", id);
		}
		
		return msg;
		
		
	}

	//method to delete all course details
	@Override
	public void deleteAllCourseDetails() {
		courserepository.deleteAll();
		log.info("All Courses details deleted  at "+new Date());
		
	}

	
	//method to find course thorugh subjectname
	@Override
	public List<CourseDTO> findBysubtName(String name) {
		List<Course> teachers =courserepository.findBysubName(name);
		List<CourseDTO> cDTO = new ArrayList<>();
		log.info("Course details found with subName "+name+"  at "+new Date());
		for(Course c: teachers)
		{
			cDTO.add(converter.convertCourseEntityToDTO(c));
		}
		return cDTO;
	}

	
	//method to assign teacher to particular course
	@Override
	public CourseDTO assignTeacherToCourse(int teacherId, int courseId) {
		Teacher teacher=teacherRepository.findById(teacherId).get();
		Course course=courserepository.findById(courseId).get();
		
		course.setTeacher(teacher);
		log.info("Teacher id assigned to course at "+new Date());
		Course course1=courserepository.save(course);
		return converter.convertCourseEntityToDTO(course1);
	}

}
