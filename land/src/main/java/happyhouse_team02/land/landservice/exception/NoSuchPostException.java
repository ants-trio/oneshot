package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class NoSuchPostException extends RuntimeException{
	public NoSuchPostException() {
		super(NO_SUCH_POST_MASSAGE);
	}

	public NoSuchPostException(String message) {
		super(message);
	}

	public NoSuchPostException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchPostException(Throwable cause) {
		super(cause);
	}
}
