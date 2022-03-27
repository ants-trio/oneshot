package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class DuplicatedBookmarkException extends RuntimeException{
	public DuplicatedBookmarkException() {
		super(DUPLICATED_BOOKMARK_MASSAGE);
	}

	public DuplicatedBookmarkException(String message) {
		super(message);
	}

	public DuplicatedBookmarkException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedBookmarkException(Throwable cause) {
		super(cause);
	}
}
