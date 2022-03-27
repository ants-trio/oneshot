package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class NoSuchMemberException extends RuntimeException {
	public NoSuchMemberException() {
		super(NO_SUCH_MEMBER_MASSAGE);
	}

	public NoSuchMemberException(String message) {
		super(message);
	}

	public NoSuchMemberException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchMemberException(Throwable cause) {
		super(cause);
	}
}
