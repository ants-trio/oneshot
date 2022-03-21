package happyhouse_team02.land.landservice.exception;

public class NoSuchBookmarkException extends RuntimeException{
	public NoSuchBookmarkException() {
		super();
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
