package happyhouse_team02.land.landservice.service.member;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoLoginException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public void login(HttpServletRequest request, String email) {
		Member findMember = findOne(email);
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_EMAIL, findMember.getEmail());
	}

	@Override
	public Member findOne(String email) {
		validateEmail(email);
		return memberRepository.findByEmail(email).orElseThrow(NoSuchMemberException::new);
	}

	@Transactional
	@Override
	public Long join(Member member) {
		return memberRepository.save(member).getId();
	}

	private void validateEmail(String loginEmail) {
		if (loginEmail == null) {
			throw new NoLoginException();
		}
	}
}