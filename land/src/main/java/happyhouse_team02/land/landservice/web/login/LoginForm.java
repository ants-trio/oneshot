package happyhouse_team02.land.landservice.web.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {

	@Email (message = "이메일 형식이 아닙니다.")
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;
}
