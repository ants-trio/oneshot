package happyhouse_team02.land.landservice.web.login.old;

import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.web.login.LoginForm;

public interface LoginValidator {

	/**
	 * 로그인이 가능한지 검사합니다.
	 */
	void validate(LoginForm loginForm, BindingResult bindingResult);

}
