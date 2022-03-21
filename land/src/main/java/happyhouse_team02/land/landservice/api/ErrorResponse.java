package happyhouse_team02.land.landservice.api;

import lombok.Data;

@Data
public class ErrorResponse {
	private int code;
	private String message;
}
