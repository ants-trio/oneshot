package happyhouse_team02.land.landservice.web.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;
}
