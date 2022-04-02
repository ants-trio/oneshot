package happyhouse_team02.land.landservice.service.member;

import javax.servlet.http.HttpServletRequest;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberService {

	/**
	 * 세션을 이용하여 로그인합니다.
	 */
	void login(HttpServletRequest request, String email);

	/**
	 * 회원을 조회합니다.
	 */
	Member findOne(String email);

	/**
	 * 회원 가입을 합니다.
	 */
	Long join(Member member);

}
