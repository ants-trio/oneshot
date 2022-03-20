package happyhouse_team02.land.landservice.web.member;

import org.springframework.validation.BindingResult;

public interface MemberValidator {

	/**
	 * 로그인이 가능한지 검사합니다.
	 */
	void validate(MemberRegisterForm registerForm, BindingResult bindingResult);

}
