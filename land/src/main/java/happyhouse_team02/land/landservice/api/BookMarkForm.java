package happyhouse_team02.land.landservice.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BookMarkForm {

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String city;

	@NotEmpty
	private String region;
}
