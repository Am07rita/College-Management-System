package edu.cms.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
