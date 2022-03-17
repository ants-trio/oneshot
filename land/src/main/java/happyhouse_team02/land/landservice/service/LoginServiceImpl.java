package happyhouse_team02.land.landservice.service;

import org.springframework.stereotype.Service;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final MemberRepository memberRepository;

	@Override
	public String login(String email, String password) {
		return memberRepository.findByEmail(email)
			.filter(member -> member.validatePassword(password))
			.map(Member::getEmail)
			.orElse("");
	}
}
