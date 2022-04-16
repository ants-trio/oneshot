package happyhouse_team02.land.landservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseResult {
	private final String state = "SUCCESS";
	private Object data;
}
