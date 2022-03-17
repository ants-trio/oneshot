package happyhouse_team02.land.landservice.exception;

public enum ExceptionMessage {
	DUPLICATED_MEMBER_MASSAGE("[ERROR] 이미 존재하는 회원입니다."),
	NO_SUCH_MEMBER_MASSAGE("[ERROR] 존재하지 않는 회원입니다."),
	NO_SUCH_POST_MASSAGE("[ERROR] 존재하지 않는 포스트입니다."),
	PASSWORD_DOES_NOT_MATCH_MASSAGE("[ERROR] 비밀번호가 일치하지 않습니다.");
	private final String message;

	ExceptionMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
