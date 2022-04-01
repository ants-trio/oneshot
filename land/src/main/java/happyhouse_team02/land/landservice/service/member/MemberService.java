package happyhouse_team02.land.landservice.service.member;

import javax.servlet.http.HttpServletRequest;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberService {

	/**
	 * 로그인
	 * 세션 키와 email 을 매핑하여 저장한다.
	 */
	void login(HttpServletRequest request, String email);

	/**
	 * 회원 조회
	 */
	Member findOne(String email);

	/**
	 * 회원 가입
	 */
	Long join(Member member);

}
