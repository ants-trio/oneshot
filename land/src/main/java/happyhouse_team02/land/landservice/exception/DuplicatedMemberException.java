package happyhouse_team02.land.landservice.exception;

public class DuplicatedMemberException extends RuntimeException {
	public DuplicatedMemberException() {
		super();
	}

	public DuplicatedMemberException(String message) {
		super(message);
	}

	public DuplicatedMemberException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedMemberException(Throwable cause) {
		super(cause);
	}
}
