package edu.cms.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.cms.entity.Teacher;
import edu.cms.model.TeacherDTO;
import edu.cms.repository.TeacherRepository;
import edu.cms.service.TeacherService;
import edu.cms.util.TeacherConverter;

@SpringBootTest
public class TeacherServiceTest {

	@Autowired//injected teacherService
	private TeacherService teacherService;
	
	@MockBean//mocked teacher repository so it will not affect on original data during testing
	private TeacherRepository teacherRepository;

	@Autowired//injected teacherconverter
	private TeacherConverter converter;
	
	
	//method for testing  to create new teacher
	@Test
	void testCreateTeacher()
	{
		Teacher teacher =Teacher.builder().firstName("Rita").lastName("Kumari").email("rita@gmail.com").
				contact("9876765434").build();
	Mockito.when(teacherRepository.save(teacher)).thenReturn(teacher);
	assertThat(teacherService.createTeacher(teacher)).isEqualTo("Teacher details saved Successfully!!");
	}
	
	
	//method for testing to get teacher details through id
	@Test
	void testGetTeacherById()
	{
		Teacher teacher =Teacher.builder().firstName("Rita").lastName("Kumari").email("rita@gmail.com").
				contact("9876765434").build();
		
	Optional<Teacher>opTeach=Optional.of(teacher);
		Mockito.when(teacherRepository.findById(teacher.getId())).thenReturn(opTeach);
	TeacherDTO dto=converter.convertTeacherEntityToDTO(teacher);
	//assertThat(teacher).isEqualTo(teacherService.getTeacherById(1));
	
	}
	
	
	//method for testing to update teacher details
	@Test
	 void testUpdateTeacher() {
		 Teacher teacher =Teacher.builder().firstName("Rita").lastName("Kumari").email("rita@gmail.com").
					contact("9876765434").build();
		 Optional<Teacher>opTeach=Optional.of(teacher);
		Teacher teacher1= opTeach.get();
		
		Mockito.when(teacherRepository.findById(teacher.getId())).thenReturn(opTeach);
	 teacher1.setFirstName("Pritam");
	 
	 Mockito.when(teacherRepository.save(teacher1)).thenReturn(teacher1);
	 
	 TeacherDTO dto1=converter.convertTeacherEntityToDTO(teacher1);
	 assertThat(teacherService.updateTeacherDetails(teacher1.getId(),teacher1).getFirstName()).isEqualTo("Pritam");
	 }
	
	
	//method for testing to delete all teachers details
	//@Test
	void  testDeleteAllTeacherDetails()
	{
		 Teacher teacher =Teacher.builder().firstName("Rita").lastName("Kumari").email("rita@gmail.com").
					contact("9876765434").build();
		 Teacher teacher1 =Teacher.builder().firstName("Amrita").lastName("Kumari").email("amr@gmail.com").
					contact(" 9878654578").build();
		 
		 List<Teacher> teacherlist=new ArrayList<>();
		 teacherlist.add(teacher);
		 teacherlist.add(teacher1);
		 
		 Mockito.when(teacherRepository.findAll()).thenReturn(teacherlist);
		 List<TeacherDTO> dto=teacherService.getAllTeacherDetails();
		 
		 List<Teacher>teach=new ArrayList<>();
		 
		 for(TeacherDTO t:dto)
		 {
			 teach.add(converter.convertDTOToTeacherEntity(t));
		 }
		 assertThat(teach).isEqualTo(teacherlist);
	}
	}


