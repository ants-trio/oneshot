package happyhouse_team02.land.landservice.web.login;

import org.springframework.validation.BindingResult;

public interface LoginValidator {

	/**
	 * 로그인이 가능한지 검사합니다.
	 */
	void validateLogin(LoginForm loginForm, BindingResult bindingResult);

}
