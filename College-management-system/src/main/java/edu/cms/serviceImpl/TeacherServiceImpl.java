package edu.cms.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.cms.entity.Teacher;
import edu.cms.exception.ResourceNotFoundException;
import edu.cms.model.TeacherDTO;
import edu.cms.repository.TeacherRepository;
import edu.cms.service.TeacherService;
import edu.cms.util.TeacherConverter;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

	//logger statically create
	private static final Logger log=LoggerFactory.getLogger(Teacher.class);
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter converter;
	
	
	//method to create Teacher
	@Override
	public String createTeacher(Teacher teacher) {
		String msg=null;
		
		teacher.setUserName(teacher.getUserName());
		teacher.setPassword(teacher.getPassword());
		
		teacherRepository.save(teacher);
		log.info("Teacher "+teacher.toString()+" added at "+new Date());
		if(teacher!=null)
		{
			msg="Teacher details saved Successfully!!";
			
		}
		return msg;
	}

	//method to get Teacher by id
	@Override
	public TeacherDTO getTeacherById(int id) {
		
		Teacher teacher = teacherRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Teacher", "Id", id));
		log.info("Teacher details get with id "+id+" at"+new Date());
		return converter.convertTeacherEntityToDTO(teacher);
	}

	//method to get all Teacher details
	@Override
	public List<TeacherDTO> getAllTeacherDetails() {
		
		List<Teacher> teachers=teacherRepository.findAll();
		List<TeacherDTO> teachDTO = new ArrayList<>();
		log.info("All teachers details found at "+new Date());
		for(Teacher t:teachers)
		{

			teachDTO.add(converter.convertTeacherEntityToDTO(t));
		}
		
		return teachDTO;
	}

	
	//method to update Teacher details
	@Override
	public TeacherDTO updateTeacherDetails(int id, Teacher teacher) {
Teacher existingT = teacherRepository.findById(id).orElseThrow(()->
new ResourceNotFoundException("Teacher", "Id", id));
		
		//we will get data from client and set in the existing teacher
		existingT.setFirstName(teacher.getFirstName());
		existingT.setLastName(teacher.getLastName());
		existingT.setContact(teacher.getContact());
		existingT.setEmail(teacher.getEmail());
		existingT.setUserName(teacher.getUserName());
		existingT.setPassword(teacher.getPassword());
		
		teacherRepository.save(existingT);
		log.info("Teacher details updated with id "+id+"  at "+new Date());
		return converter.convertTeacherEntityToDTO(existingT);
		
	}

	
	//method to delete Teacher by id
	@Override
	public String deleteTeacherById(int id) {
		String msg = null;
		Optional<Teacher> teacher=teacherRepository.findById(id);
		if(teacher.isPresent()) 
		{
			teacherRepository.deleteById(id);
			log.info("Teacher details deleted with id "+id+"  at "+new Date());
			msg="Teacher with id "+id+" has been Deleted!!"; 
		}
		else
		{
			throw new ResourceNotFoundException("Teacher","Id", id);
		}
		
		return msg;
		
	}

	
	//method to delete all Teacher
	@Override
	public void deleteAllTeacherDetails() {
		teacherRepository.deleteAll();
		log.info("All Teachers details deleted  at "+new Date());
	}

	
	//method to find Teacher thorugh email
	@Override
	public TeacherDTO findTeacherByEmail(String email) {
		Teacher temail=teacherRepository.findByEmail(email);
		log.info("Teacher details updated with email "+email+"  at "+new Date());
		return converter.convertTeacherEntityToDTO(temail);
	}

	
	//method to find teacher by first name.
	@Override
	public List<TeacherDTO> findByFirstName(String name) {
		List<Teacher> teachers =teacherRepository.findByFirstName(name);
		List<TeacherDTO> tDTO = new ArrayList<>();
		log.info("Teacher details found with firstName "+name+"  at "+new Date());
		for(Teacher t: teachers)
		{
			tDTO.add(converter.convertTeacherEntityToDTO(t));
		}
		return tDTO;
	}

}
