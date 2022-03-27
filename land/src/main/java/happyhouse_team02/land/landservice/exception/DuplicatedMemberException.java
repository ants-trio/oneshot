package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class DuplicatedMemberException extends RuntimeException {
	public DuplicatedMemberException() {
		super(DUPLICATED_MEMBER_MASSAGE);
	}

	public DuplicatedMemberException(String message) {
		super(message);
	}

	public DuplicatedMemberException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedMemberException(Throwable cause) {
		super(cause);
	}
}
