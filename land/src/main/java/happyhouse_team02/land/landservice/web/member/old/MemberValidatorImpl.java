package happyhouse_team02.land.landservice.web.member.old;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import happyhouse_team02.land.landservice.web.member.MemberRegisterForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberValidatorImpl implements MemberValidator {

	private final MemberRepository memberRepository;

	@Override
	public void validate(MemberRegisterForm registerForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return;
		}
		validateDuplicatedEmail(registerForm, bindingResult);

	}

	private void validateDuplicatedEmail(MemberRegisterForm registerForm, BindingResult bindingResult) {
		Optional<Member> findMember = memberRepository.findByEmail(registerForm.getEmail());
		if (findMember.isPresent()){
			bindingResult.reject("alreadyRegistered");
			return;
		}
		validatePasswordAndConfirmPassword(registerForm, bindingResult);
	}

	private void validatePasswordAndConfirmPassword(MemberRegisterForm registerForm, BindingResult bindingResult) {
		if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
			bindingResult.reject("wrongPassword");
		}
	}
}
