package happyhouse_team02.land.landservice.repository.member;

import java.util.List;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoLoginException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidatedRepositoryImpl implements MemberValidatedRepository {

	private final MemberRepository memberRepository;

	@Override
	public void save(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member getMember(String loginEmail) {
		validateEmail(loginEmail);
		return memberRepository.findByEmail(loginEmail).orElseThrow(NoSuchMemberException::new);
	}

	@Override
	public Member getMember(Long id) {
		return memberRepository.findById(id).orElseThrow(NoSuchMemberException::new);
	}

	@Override
	public List<Member> getMembers() {
		return memberRepository.findAll();
	}

	private void validateEmail(String loginEmail) {
		if (loginEmail == null){
			throw new NoLoginException();
		}
	}
}
