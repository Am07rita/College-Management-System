package edu.cms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import edu.cms.entity.Teacher;
import edu.cms.model.TeacherDTO;

@Component
public class TeacherConverter {
//convert from teacherDTO to entity
	public Teacher convertDTOToTeacherEntity(TeacherDTO teacherDTO)
	{
		Teacher teacher=new Teacher();
		if(teacher!=null)
		{
			
			BeanUtils.copyProperties(teacherDTO, teacher);
		}
		return teacher;
	}
	//convert from Teacherentity to TeacherDTO
	public TeacherDTO convertTeacherEntityToDTO(Teacher teacher) {
	
		TeacherDTO teacherDTO=new TeacherDTO();
		if(teacher!=null)
		{
			BeanUtils.copyProperties(teacher, teacherDTO);
		}
		return teacherDTO;
	}
}
