package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class UnauthorizedAccessException extends RuntimeException{
	public UnauthorizedAccessException() {
		super(UNAUTHORIZED_ACCESS_MESSAGE);
	}

	public UnauthorizedAccessException(String message) {
		super(message);
	}

	public UnauthorizedAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedAccessException(Throwable cause) {
		super(cause);
	}
}
