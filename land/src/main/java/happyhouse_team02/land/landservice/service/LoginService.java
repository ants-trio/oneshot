package happyhouse_team02.land.landservice.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	/**
	 * 로그인
	 * 세션 키와 email 을 매핑하여 저장한다.
	 */
	void login(HttpServletRequest request, String email);
}
