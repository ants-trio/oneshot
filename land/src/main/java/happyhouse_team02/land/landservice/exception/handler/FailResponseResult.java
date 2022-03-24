package happyhouse_team02.land.landservice.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailResponseResult {
	private String state;
	private String message;
}
