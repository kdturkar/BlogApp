package com.App.BlogApplication.DTO;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotNull
	@NotEmpty
	@Size(min = 3, message = "Name must be min of 3 characters!!!")

	private String name;

	@Email(message = "Email is not valid!!!")
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars")
	private String password;

	@NotNull
	@NotEmpty
	private String about;

	private Set<RoleDto> roles = new HashSet<>();

}
