package edu.cms.model;

import edu.cms.entity.Teacher;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CourseDTO {
	@NotNull(message="subId cannot be null")
	private int subId;
	@NotNull(message="Subject Name cannot be null")
	private String subName;
	@NotNull(message="Price cannot be null")
	private double price;
	
	private Teacher teacher;
}
