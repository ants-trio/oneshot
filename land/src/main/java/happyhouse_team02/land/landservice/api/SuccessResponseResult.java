package happyhouse_team02.land.landservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseResult<T> {
	private final String state = "SUCCESS";
	private T data;
}
