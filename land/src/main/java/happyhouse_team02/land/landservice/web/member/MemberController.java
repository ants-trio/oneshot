package happyhouse_team02.land.landservice.web.member;

import static happyhouse_team02.land.landservice.web.util.strategy.Strategy.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.member.MemberService;
import happyhouse_team02.land.landservice.web.util.strategy.ValidatorContext;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final ValidatorContext validatorContext;

	@GetMapping
	public String login() {
		return "member/login";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("memberRegisterForm", new MemberRegisterForm());
		return "member/register";
	}

	@PostMapping("/new")
	public String create(@Validated @ModelAttribute MemberRegisterForm form, BindingResult bindingResult) {
		validatorContext.validate(form, bindingResult, MEMBER_STRATEGY);

		if (bindingResult.hasErrors()) {
			return "member/register";
		}

		Member member = new Member.Builder()
			.email(form.getEmail())
			.password(form.getPassword())
			.build();

		memberService.join(member);
		return "redirect:/";
	}
}
