package happyhouse_team02.land.landservice.web.login;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import happyhouse_team02.land.landservice.web.util.strategy.ValidatorStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginStrategy implements ValidatorStrategy {

	private final MemberRepository memberRepository;

	@Override
	public void call(Object form, BindingResult bindingResult) {
		if (form instanceof LoginForm) {
			validateEmailAndPassword((LoginForm)form, bindingResult);
			return;
		}
		throw new IllegalArgumentException();
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
	}
}
