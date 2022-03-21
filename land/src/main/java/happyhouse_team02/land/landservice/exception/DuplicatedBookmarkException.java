package happyhouse_team02.land.landservice.exception;

public class DuplicatedBookmarkException extends RuntimeException{
	public DuplicatedBookmarkException() {
		super();
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
