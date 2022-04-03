package happyhouse_team02.land.landservice.exception;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

public class NoSuchCommentException extends RuntimeException{
	public NoSuchCommentException() {
		super(NO_SUCH_COMMENT_MESSAGE);
	}

	public NoSuchCommentException(String message) {
		super(message);
	}

	public NoSuchCommentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchCommentException(Throwable cause) {
		super(cause);
	}
}
