package happyhouse_team02.land.landservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import happyhouse_team02.land.landservice.web.util.argumentresolver.LoginEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String home(@LoginEmail String loginEmail, Model model) {
		if (loginEmail == null) {
			return "index";
		}
		model.addAttribute("loginEmail", loginEmail);
		return "loginIndex";
	}

}
