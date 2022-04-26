package happyhouse_team02.land.landservice.service.member;

import static happyhouse_team02.land.landservice.web.util.session.SessionConst.*;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.DuplicatedMemberException;
import happyhouse_team02.land.landservice.exception.NoLoginException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.MemberRepository;
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
		validateDuplicated(member.getEmail());
		return memberRepository.save(member).getId();
	}

	private void validateDuplicated(String email) {
		Optional<Member> findMember = memberRepository.findByEmail(email);
		if (findMember.isPresent()){
			throw new DuplicatedMemberException();
		}
	}

	private void validateEmail(String loginEmail) {
		if (loginEmail == null) {
			throw new NoLoginException();
		}
	}
}