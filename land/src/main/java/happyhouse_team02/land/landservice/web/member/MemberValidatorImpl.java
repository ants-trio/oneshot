package happyhouse_team02.land.landservice.web.member;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberValidatorImpl implements MemberValidator {

	private final MemberService memberService;

	@Override
	public void validate(MemberRegisterForm registerForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return;
		}
		validateDuplicatedEmail(registerForm, bindingResult);

	}

	private void validateDuplicatedEmail(MemberRegisterForm registerForm, BindingResult bindingResult) {
		Optional<Member> findMember = memberService.findOne(registerForm.getEmail());
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
