package happyhouse_team02.land.landservice.exception;

public class PasswordDoesNotMatchException extends RuntimeException {
	public PasswordDoesNotMatchException() {
		super();
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
