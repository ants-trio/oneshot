package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class NoSuchBookmarkException extends RuntimeException{
	public NoSuchBookmarkException() {
		super(NO_SUCH_BOOKMARK_MASSAGE);
	}

	public NoSuchBookmarkException(String message) {
		super(message);
	}

	public NoSuchBookmarkException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchBookmarkException(Throwable cause) {
		super(cause);
	}
}
