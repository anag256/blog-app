package com.projects.blogs.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserBean {
	private int id;
	@NotEmpty
	@Size(min=4,message="Username must be min of 4 characters !!")
	private String name;
	@Email(message="Email address is not valid")
	private String email;
	@NotEmpty
	private String about;
	@NotEmpty
	@Size(min=4,max=10,message="Password must be minimum of 4 and max of 10 character")
//	@Pattern(regexp="") for regex
	private String password;

}
