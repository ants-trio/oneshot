package happyhouse_team02.land.landservice.web.util.interceptor;

import static happyhouse_team02.land.landservice.web.util.session.SessionConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute(LOGIN_EMAIL) == null) {
			log.info("미인증 사용자 요청: {}", requestURI);
			response.sendRedirect("/login?redirectURL=" + requestURI);
			// response.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
			// API Controller 면 미인증으로 보내주면 되겠다.
			return false;
		}
		return true;
	}
}
