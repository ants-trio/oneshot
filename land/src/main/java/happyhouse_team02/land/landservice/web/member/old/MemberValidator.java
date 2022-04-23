package happyhouse_team02.land.landservice.web.member.old;

import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.web.member.MemberRegisterForm;

public interface MemberValidator {

	/**
	 * 아이디를 만들 수 있는지 검사합니다.
	 */
	void validate(MemberRegisterForm registerForm, BindingResult bindingResult);

}
