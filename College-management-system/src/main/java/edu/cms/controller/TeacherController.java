
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

	import edu.cms.entity.Teacher;
	import edu.cms.model.TeacherDTO;
	import edu.cms.service.TeacherService;
	import edu.cms.util.TeacherConverter;
	import javax.validation.Valid;

	@RestController
	@RequestMapping("/teacher")//mapping to Teacher
	public class TeacherController {
		
         @Autowired //injected Teacherservice here
		private TeacherService teacherService;
		
		
		@Autowired //injected Teacherconverter here
		private TeacherConverter converter;
		
		//mapping to create Teacher
		@PostMapping("/createTeacher")
		public String createTeacher(@Valid @RequestBody TeacherDTO teacherDTO)
		{
			final Teacher teacher = converter.convertDTOToTeacherEntity(teacherDTO);
			return teacherService.createTeacher(teacher);
		}
		
		
		//mapping to get Teacherby particular id
		@GetMapping("/getTeacherById/{id}")
		public TeacherDTO getTeacherById(@PathVariable("id") int id)
		{
			return teacherService.getTeacherById(id);
		} 
		
		//mapping to get all Teachers details
		@GetMapping("/getAllTeachers")
		public List<TeacherDTO> getAllTeacherDetails()
		{
			return teacherService.getAllTeacherDetails();
		}
		
		//mapping to update Teacher by particular id
		@PutMapping("/updateTeacher/{id}")
		public TeacherDTO updateTeacherDetails(@Valid @PathVariable("id") int id,
				@RequestBody TeacherDTO teacherDTO)
		{
			Teacher teacher = converter.convertDTOToTeacherEntity(teacherDTO);
			return teacherService.updateTeacherDetails(id, teacher);
		}
		
		//mapping to delete Teacher by particular id
		@DeleteMapping("/deleteTeacherById/{id}")
		public String deleteTeacherById(@PathVariable("id") int id)
		{
			return teacherService.deleteTeacherById(id);
		}
		
		//mapping to delete all Teacher details
		@DeleteMapping("/deleteAllTeachers")
		public ResponseEntity<String> deleteAllTeachers()
		{
			teacherService.deleteAllTeacherDetails();
			return new ResponseEntity<String>("All teacher details have been deleted successfully!", HttpStatus.OK);
		}
		
		//mapping to get Teacher by particular email
		@GetMapping("/getTeacherByEmail/{email}")
		public TeacherDTO getTeacherByEmail(@PathVariable("email") String email)
		{
			return teacherService.findTeacherByEmail(email);
		}
		
		//mapping to get Teacher by particular name
		@GetMapping("/getTeacherByFirstName/{name}")
		public List<TeacherDTO> getTeacherByFirstName(@PathVariable("name") String name)
		{
			return teacherService.findByFirstName(name);
		}
	}


