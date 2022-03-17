package happyhouse_team02.land.landservice.web.login;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import happyhouse_team02.land.landservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		return "login";
	}

	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
						HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			log.info("bindingResult={}", bindingResult.getAllErrors());
			return "login";
		}
		// TODO
		String loginEmail = loginService.login(form.getEmail(), form.getPassword());
		if (loginEmail.isBlank()) {
			bindingResult.reject("validateFail");
			return "login";
		}

		addMemberToSession(request, loginEmail);
		return "redirect:/";
	}

	private void addMemberToSession(HttpServletRequest request, String loginEmail) {
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_MEMBER, loginEmail);
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		expireSession(request);
		return "redirect:/";
	}

	@GetMapping("/posts/logout")
	public String postsLogout(HttpServletRequest request) {
		expireSession(request);
		return "redirect:/posts";
	}

	private void expireSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		log.info("session={}", session);
		if(session != null){
			session.invalidate();
		}
	}
}
