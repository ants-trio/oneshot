package happyhouse_team02.land.landservice.service.member;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoLoginException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidatorImpl implements MemberValidator{

	private final MemberRepository memberRepository;

	@Override
	public Member getMember(String loginEmail) {
		validateEmail(loginEmail);
		return memberRepository.findByEmail(loginEmail).orElseThrow(NoSuchMemberException::new);
	}

	private void validateEmail(String loginEmail) {
		if (loginEmail == null){
			throw new NoLoginException();
		}
	}
}
