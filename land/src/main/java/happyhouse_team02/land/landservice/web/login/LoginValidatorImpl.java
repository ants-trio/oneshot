package happyhouse_team02.land.landservice.web.login;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.MemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginValidatorImpl implements LoginValidator {

	private final MemberService memberService;

	@Override
	public void validate(LoginForm loginForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) { // TODO
			return;
		}
		validateEmailAndPassword(loginForm, bindingResult);
	}

	private void validateEmailAndPassword(LoginForm loginForm, BindingResult bindingResult) {
		Optional<Member> findMember = memberService.findOne(loginForm.getEmail());
		if (findMember.isEmpty()) {
			bindingResult.reject("absentEmail");
			return;
		}
		validatePassword(findMember.get(), loginForm.getPassword(), bindingResult);
	}

	private void validatePassword(Member member, String password, BindingResult bindingResult) {
		if (!member.isValidatePassword(password)) {
			bindingResult.reject("validateFail");
		}
	}
}