package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class NoLoginException extends RuntimeException{
	public NoLoginException() {
		super(NEED_LOGIN_MESSAGE);
	}

	public NoLoginException(String message) {
		super(message);
	}

	public NoLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoLoginException(Throwable cause) {
		super(cause);
	}
}
