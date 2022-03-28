package happyhouse_team02.land.landservice.web.login;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.member.MemberValidatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginValidatorImpl implements LoginValidator {

	private final MemberValidatedRepository memberRepository;

	@Override
	public void validate(LoginForm loginForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) { // TODO
			return;
		}
		validateEmailAndPassword(loginForm, bindingResult);
	}

	private void validateEmailAndPassword(LoginForm loginForm, BindingResult bindingResult) {
		Member findMember = memberRepository.getMember(loginForm.getEmail());
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