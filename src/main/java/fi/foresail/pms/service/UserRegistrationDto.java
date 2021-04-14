package fi.foresail.pms.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

	@NotEmpty
	private String name;


	@NotEmpty
	private String password;

	@Email
	@NotEmpty
	private String email;

}