package happyhouse_team02.land.landservice.service;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final MemberRepository memberRepository;

	@Override
	public void login(HttpServletRequest request, String email) {
		String findEmail = memberRepository.findByEmail(email).map(Member::getEmail).orElse("");
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_MEMBER_EMAIL, findEmail);
	}
}
