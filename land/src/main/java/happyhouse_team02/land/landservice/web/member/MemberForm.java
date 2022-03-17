package happyhouse_team02.land.landservice.web.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

	@NotBlank
	// TODO
	// @Pattern(regexp = "/^[가-힣a-zA-Z]+$/", message = "한글또는 영어만 입력가능합니다.")
	private String firstName;

	@NotBlank
	// TODO
	// @Pattern(regexp = "/^[가-힣a-zA-Z]+$/", message = "한글또는 영어만 입력가능합니다.")
	private String lastName;

	@Email
	@NotEmpty(message = "이메일을 입력해주세요.")
	private String email;

	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String password;

	@NotEmpty(message = "비밀번호 확인을 입력해주세요.")
	private String confirmPassword;
}