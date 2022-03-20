package happyhouse_team02.land.landservice.web.post;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import happyhouse_team02.land.landservice.service.PostService;
import happyhouse_team02.land.landservice.service.PostSummaryDTO;
import happyhouse_team02.land.landservice.web.argumentresolver.LoginEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	@GetMapping
	public String postList(@LoginEmail String loginEmail,
						   Model model) {
		validateLogin(model, loginEmail);
		log.info("loginEmail={}", loginEmail);
		List<PostSummaryDTO> posts = postService.findPostsSummary();
		model.addAttribute("posts", posts);
		return "posts/postList";
	}

	private void validateLogin(Model model, String loginMemberId) {
		if (loginMemberId == null){
			model.addAttribute("loginMemberId", "");
			return;
		}
		model.addAttribute("loginMemberId", loginMemberId);
	}

	@GetMapping("/new")
	public String postForm(@SessionAttribute(name = LOGIN_MEMBER_EMAIL, required = false) String loginMemberId,
						   Model model) {
		return "posts/postWrite";
	}

}
