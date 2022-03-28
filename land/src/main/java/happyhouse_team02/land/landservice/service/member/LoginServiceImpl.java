package happyhouse_team02.land.landservice.service.member;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.member.MemberRepository;
import happyhouse_team02.land.landservice.repository.member.MemberValidatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

	private final MemberRepository memberRepository;
	private final MemberValidatedRepository memberValidatedRepository;

	@Override
	public void login(HttpServletRequest request, String email) {
		Member findMember = memberValidatedRepository.getMember(email);
		String findEmail = memberRepository.findByEmail(email)
			.map(Member::getEmail)
			.orElseThrow(NoSuchMemberException::new);

		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_EMAIL, findEmail);
	}
}
