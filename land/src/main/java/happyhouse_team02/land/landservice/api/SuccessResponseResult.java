package happyhouse_team02.land.landservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseResult<T> {
	private static final String state = "SUCCESS";
	private T data;
}
