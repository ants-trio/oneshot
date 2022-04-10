package happyhouse_team02.land.landservice.web.member;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import happyhouse_team02.land.landservice.web.util.strategy.ValidatorStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberStrategy implements ValidatorStrategy {

	private final MemberRepository memberRepository;

	@Override
	public void call(Object form, BindingResult bindingResult) {
		validateDuplicatedEmail((MemberRegisterForm) form, bindingResult);
	}

	private void validateDuplicatedEmail(MemberRegisterForm registerForm, BindingResult bindingResult) {
		Optional<Member> findMember = memberRepository.findByEmail(registerForm.getEmail());
		if (findMember.isPresent()){
			bindingResult.reject("alreadyRegistered");
			return;
		}
		validatePasswordAndConfirmPassword(registerForm, bindingResult);
		// TODO: 리팩터링 대상 체인형식으로 순서를 주입하고 인터셉터나 필터방식으로 순차적 검증을 진행할 수 있게 만들어 보자.
	}

	private void validatePasswordAndConfirmPassword(MemberRegisterForm registerForm, BindingResult bindingResult) {
		if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
			bindingResult.reject("wrongPassword");
		}
	}
}
