package edu.cms.service;

import java.util.List;

import edu.cms.entity.Teacher;
import edu.cms.model.TeacherDTO;

public interface TeacherService{
	
	 String createTeacher(Teacher teacher); 	//method to create Teacher
       TeacherDTO getTeacherById(int id);		//method to get Teacher by id
       List<TeacherDTO> getAllTeacherDetails();		//method to get all Teacher details
       TeacherDTO updateTeacherDetails(int id, Teacher teacher);		//method to update Teacher details
   	   String deleteTeacherById(int id);		//method to delete Teacher by id
   	   void deleteAllTeacherDetails();		//method to delete all Teacher
   	   TeacherDTO findTeacherByEmail(String email);		//method to find Teacher thorugh email
       List<TeacherDTO> findByFirstName(String name);		//method to find teacher by first name.
}	
