package happyhouse_team02.land.landservice.api.post;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.exception.NoLoginException;
import happyhouse_team02.land.landservice.service.MemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostValidatorImpl implements PostValidator{

	private final MemberService memberService;

	@Override
	public void validateEmail(String loginEmail) {
		if (loginEmail == null){
			throw new NoLoginException();
		}
	}
}
