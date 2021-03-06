package happyhouse_team02.land.landservice.exception;

public interface ExceptionMessage {
	/**
	 * 예외처리 실패의 경우 code
	 */
	String FAIL = "FAIL";

	/**
	 * 글로벌 오류 메시지
	 */
	String GLOBAL_MESSAGE = "[ERROR] 내부 오류";

	/**
	 * 잘못된 접근 메시지
	 */
	String ILLEGAL_MESSAGE = "[ERROR] 잘못된 접근입니다.";

	/**
	 *  권한이 없는 경우
	 */
	String UNAUTHORIZED_ACCESS_MESSAGE = "[ERROR] 접근 권한이 없습니다.";

	/**
	 * 로그인이 필요한 경우
	 */
	String NEED_LOGIN_MESSAGE = "[ERROR] 로그인이 필요합니다.";

	/**
	 * 조회한 회원이 이미 가입되어 있는 경우
	 */
	String DUPLICATED_MEMBER_MESSAGE = "[ERROR] 이미 존재하는 회원입니다.";

	/**
	 * 조회한 회원을 찾을 수 없는 경우
	 */
	String NO_SUCH_MEMBER_MESSAGE = "[ERROR] 존재하지 않는 회원입니다.";

	/**
	 * 비밀번호가 일치하지 않는 경우
	 */
	String PASSWORD_DOES_NOT_MATCH_MESSAGE = "[ERROR] 비밀번호가 일치하지 않습니다.";

	/**
	 * 조회한 게시물을 찾을 수 없는 경우
	 */
	String NO_SUCH_POST_MESSAGE = "[ERROR] 존재하지 않는 포스트입니다.";

	/**
	 * 조회한 북마크를 찾을 수 없는 경우
	 */
	String NO_SUCH_BOOKMARK_MESSAGE = "[ERROR] 존재하지 않는 북마크입니다.";

	/**
	 * 조회한 북마크가 이미 있는 경우
	 */
	String DUPLICATED_BOOKMARK_MESSAGE = "[ERROR] 이미 존재하는 북마크입니다.";

	/**
	 * 조회한 댓글을 찾을 수 없는 경우
	 */
	String NO_SUCH_COMMENT_MESSAGE = "[ERROR] 존재하지 않는 댓글입니다.";
}
