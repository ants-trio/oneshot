package happyhouse_team02.land.landservice.web;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final MemberRepository memberRepository;

	@GetMapping("/")
	public String home(@SessionAttribute(name = LOGIN_MEMBER, required = false) String loginMemberId, Model model) {
		if (loginMemberId == null) {
			return "index";
		}
		model.addAttribute("loginMemberId", loginMemberId);
		return "loginIndex";
	}

}
