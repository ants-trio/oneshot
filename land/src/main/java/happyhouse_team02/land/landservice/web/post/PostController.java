package happyhouse_team02.land.landservice.web.post;

import static happyhouse_team02.land.landservice.web.session.SessionConst.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import happyhouse_team02.land.landservice.service.post.PostService;
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
	public String postList(@LoginEmail String loginEmail, Model model) {
		validateLogin(model, loginEmail);
		log.info("loginEmail={}", loginEmail);
		// List<PostSummaryDto> posts = postService.findPostsSummary();
		// model.addAttribute("posts", posts);
		return "posts/postList";
	}


	@GetMapping("/new")
	public String postForm(@SessionAttribute(name = LOGIN_EMAIL, required = false) String loginMemberId, Model model) {
		return "posts/postWrite";
	}

	@GetMapping("/{postId}")
	public String post(@LoginEmail String loginEmail, @PathVariable Long postId, Model model) {
		validateLogin(model, loginEmail);
		model.addAttribute("postId", postId);
		return "posts/postView";
	}

	private void validateLogin(Model model, String loginMember) {
		if (loginMember == null) {
			model.addAttribute("loginMember", "");
			return;
		}
		model.addAttribute("loginMember", loginMember);
	}
}
