package happyhouse_team02.land.landservice.util;

import static happyhouse_team02.land.landservice.web.util.session.SessionConst.*;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<String> {

	private final HttpSession session;

	@Override
	public Optional<String> getCurrentAuditor() {
		Object loginEmail = session.getAttribute(LOGIN_EMAIL);
		return Optional.ofNullable((String)loginEmail);
	}
}
