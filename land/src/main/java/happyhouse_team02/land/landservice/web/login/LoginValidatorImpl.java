package happyhouse_team02.land.landservice.web.login;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginValidatorImpl implements LoginValidator {

	private final MemberRepository memberRepository;

	@Override
	public void validateLogin(LoginForm loginForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return;
		}
		validateEmailAndPassword(loginForm, bindingResult);
	}

	private void validateEmailAndPassword(LoginForm loginForm, BindingResult bindingResult) {
		Optional<Member> findMember = memberRepository.findByEmail(loginForm.getEmail());
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
		;
	}
}