package happyhouse_team02.land.landservice.exception;

public class NoSuchMemberException extends RuntimeException {
	public NoSuchMemberException() {
		super();
	}

	public NoSuchMemberException(String message) {
		super(message);
	}

	public NoSuchMemberException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchMemberException(Throwable cause) {
		super(cause);
	}
}
