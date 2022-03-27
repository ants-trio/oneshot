package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class PasswordDoesNotMatchException extends RuntimeException {
	public PasswordDoesNotMatchException() {
		super(PASSWORD_DOES_NOT_MATCH_MASSAGE);
	}

	public PasswordDoesNotMatchException(String message) {
		super(message);
	}

	public PasswordDoesNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordDoesNotMatchException(Throwable cause) {
		super(cause);
	}
}
