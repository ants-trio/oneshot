package happyhouse_team02.land.landservice.service;

public interface LoginService {

	/**
	 * @return 빈 문자열이면 로그인 실패
	 */
	String login(String email, String password);

}
