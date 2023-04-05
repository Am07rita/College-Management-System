package edu.cms.model;

import java.util.List;

import edu.cms.entity.Course;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO extends UserDTO {
@NotNull(message="First Name cannot be null")
	private String firstName;
@NotNull(message="Last Name cannot be null")
	private String lastName;
@Email(message="Please enter correct email")
@NotNull(message="Email cannot be null")
	private String email;
@Pattern(regexp = "^\\d{10}$",message="Contact should have 10 digits")
@NotNull(message="Contact cannot be null")
	private String contact;
	
	private List<Course> course;
}
