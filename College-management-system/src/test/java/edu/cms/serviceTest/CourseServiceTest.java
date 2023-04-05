package edu.cms.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;
import edu.cms.repository.CourseRepository;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;

@SpringBootTest
public class CourseServiceTest {
	
	@Autowired//injected courseService
	private CourseService courseService;
	
	@MockBean//mocked course repository so it will not affect on original data during testing
	private CourseRepository courserepository;
	
	@Autowired//injected teacherconverter
	private CourseConverter converter;
	
	//method for testing  to create new course
	@Test
	void testCreateCourse() {
		Course course =Course.builder().subId(105).subName("Java").price(5000.80).build();
		Mockito.when(courserepository.save(course)).thenReturn(course);
		assertThat(courseService.createCourse(course)).isEqualTo("Course details saved successfully!!");
	}

	//method for testing to get course details through id
	@Test
	void testGetCourseById()
	{
		Course course=Course.builder().subId(105).subName("Java").price(5000.80).build();
		Optional<Course>opcours=Optional.of(course);
		Mockito.when(courserepository.findById(course.getSubId())).thenReturn(opcours);
		CourseDTO dto=converter.convertCourseEntityToDTO(course);
		assertThat(course).isEqualTo(courseService.getCourseById(105));
	}
	
	
	//method for testing to update teacher details 
	@Test
	 void testUpdateCourse() {
		 Course course=Course.builder().subId(105).subName("Java").price(5000.80).build();
		 Optional<Course>opCours=Optional.of(course);
		 Course course1= opCours.get();
		Mockito.when(courserepository.findById(course.getSubId())).thenReturn(opCours);
	course1.setSubName("AWS");
	 
	 Mockito.when(courserepository.save(course1)).thenReturn(course1);
	 
	 CourseDTO dto1=converter.convertCourseEntityToDTO(course1);
	 assertThat(courseService.updateCourseDetails(course1.getSubId(),course1).getSubName());
	 }
	
	//method for testing to delete all courses details
		@Test
		void  testDeleteAllCourseDetails()
		{
			Course course=Course.builder().subId(102).subName("Java").price(5000.80).build();
			Course course1=Course.builder().subId(101).subName("OS").price(15000.80).build();
			 
			 List<Course> courselist=new ArrayList<>();
			 courselist.add(course);
			 courselist.add(course1);
			 
			 Mockito.when(courserepository.findAll()).thenReturn(courselist);
			 List<CourseDTO> dto=courseService.getAllCourseDetails();
			 
			 List<Course>cours=new ArrayList<>();
			 
			 for(CourseDTO t:dto)
			 {
				 cours.add(converter.convertDTOToCourseEntity(t));
			 }
			 assertThat(cours).isEqualTo(courselist);
		}
}
