package happyhouse_team02.land.landservice.exception;

public class DuplicatedMemberException extends RuntimeException {
	public DuplicatedMemberException() {
		super();
	}

	public DuplicatedMemberException(ExceptionMessage message) {
		super(message.toString());
	}

	public DuplicatedMemberException(ExceptionMessage message, Throwable cause) {
		super(message.toString(), cause);
	}

	public DuplicatedMemberException(Throwable cause) {
		super(cause);
	}
}
