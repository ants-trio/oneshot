package happyhouse_team02.land.landservice.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final MemberService memberService;
	private final LoginValidator loginValidator;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		return "member/login";
	}

	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("loginForm") LoginForm form,
						BindingResult bindingResult,
						@RequestParam(defaultValue = "/") String redirectURL,
						HttpServletRequest request) {

		loginValidator.validate(form, bindingResult);

		if (bindingResult.hasErrors()) {
			return "member/login";
		}

		memberService.login(request, form.getEmail());
		return "redirect:" + redirectURL;
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
		if(session != null){
			session.invalidate();
		}
	}
}
