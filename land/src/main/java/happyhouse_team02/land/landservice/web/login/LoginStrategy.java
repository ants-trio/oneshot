package happyhouse_team02.land.landservice.web.login;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.member.MemberService;
import happyhouse_team02.land.landservice.web.util.strategy.ValidatorStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginStrategy implements ValidatorStrategy {

	private final MemberService memberService;

	@Override
	public void call(Object form, BindingResult bindingResult) {
		if (form instanceof LoginForm) {
			validateEmailAndPassword((LoginForm)form, bindingResult);
			return;
		}
		throw new IllegalArgumentException();
	}

	private void validateEmailAndPassword(LoginForm loginForm, BindingResult bindingResult) {
		Member findMember = memberService.findOne(loginForm.getEmail());
		if (findMember == null) {
			bindingResult.reject("absentEmail");
			return;
		}
		validatePassword(findMember, loginForm.getPassword(), bindingResult);
	}

	private void validatePassword(Member member, String password, BindingResult bindingResult) {
		if (!member.isValidatePassword(password)) {
			bindingResult.reject("validateFail");
		}
	}
}
