package happyhouse_team02.land.landservice.service;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;
import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

	private final MemberRepository memberRepository;

	@Override
	public void login(HttpServletRequest request, String email) {
		String findEmail = memberRepository.findByEmail(email)
			.map(Member::getEmail)
			.orElseThrow(() -> new NoSuchMemberException(NO_SUCH_MEMBER_MASSAGE));

		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_EMAIL, findEmail);
	}
}
