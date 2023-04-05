package edu.cms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private int id;
	@NotNull(message="UserName cannot be null")
	private String userName;
	@Size(min=6)
	@NotNull(message="Password cannot be null")
	private String password;
}
