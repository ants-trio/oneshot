package happyhouse_team02.land.landservice.exception;

public interface ExceptionMessage {
	/**
	 * 조회한 회원이 이미 가입되어 있는 경우
	 */
	String DUPLICATED_MEMBER_MASSAGE = "[ERROR] 이미 존재하는 회원입니다.";

	/**
	 * 조회한 회원을 찾을 수 없는 경우
	 */
	String 	NO_SUCH_MEMBER_MASSAGE = "[ERROR] 존재하지 않는 회원입니다.";

	/**
	 * 조회한 게시물을 찾을 수 없는 경우
	 */
	String  NO_SUCH_POST_MASSAGE = "[ERROR] 존재하지 않는 포스트입니다.";

	/**
	 * 비밀번호가 일치하지 않는 경우
	 */
	String PASSWORD_DOES_NOT_MATCH_MASSAGE = "[ERROR] 비밀번호가 일치하지 않습니다.";

}
