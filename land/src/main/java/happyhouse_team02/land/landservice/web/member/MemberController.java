package happyhouse_team02.land.landservice.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Name;
import happyhouse_team02.land.landservice.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping
	public String login() {
		return "login";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "login/register";
	}

	@PostMapping("/new")
	public String create(@Validated @ModelAttribute("memberForm") MemberForm form, BindingResult bindingResult) {
		validateMember(form, bindingResult);

		if (bindingResult.hasErrors()) {
			return "login/register";
		}

		Name name = new Name(form.getFirstName(), form.getLastName());
		Member member = new Member.Builder()
			.name(name)
			.email(form.getEmail())
			.password(form.getPassword())
			.build();

		memberService.join(member);
		return "redirect:/";
	}

	private void validateMember(MemberForm form, BindingResult bindingResult) {
		validateDuplicated(form, bindingResult);
		validatePassword(form, bindingResult);
	}

	private void validateDuplicated(MemberForm form, BindingResult bindingResult) {
		memberService.findOne(form.getEmail()).ifPresent(member -> bindingResult.reject("alreadyRegistered"));
	}

	private void validatePassword(MemberForm form, BindingResult result) {
		if (!form.getPassword().equals(form.getConfirmPassword())){
			result.rejectValue("confirmPassword","wrongPassword");
		}
	}
}
