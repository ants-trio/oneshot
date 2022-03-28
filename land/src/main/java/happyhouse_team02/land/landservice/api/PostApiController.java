package happyhouse_team02.land.landservice.api;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.service.post.PostDetailDto;
import happyhouse_team02.land.landservice.service.post.PostDto;
import happyhouse_team02.land.landservice.service.post.PostService;
import happyhouse_team02.land.landservice.service.post.PostSummaryDto;
import happyhouse_team02.land.landservice.web.argumentresolver.LoginEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostApiController {

	private final PostService postService;


	@GetMapping
	public SuccessResponseResult getPosts(@RequestParam int pageNo,
										  @RequestParam int amount) {
		Long total = postService.countPosts();
		List<PostSummaryDto> postsSummary = postService.findPostsSummary(pageNo, amount);

		return new SuccessResponseResult(new GetPostsResponse(total, postsSummary));
	}

	@PostMapping("/new")
	public SuccessResponseResult writePost(@LoginEmail String loginEmail,
										   @Validated @RequestBody WritePostRequest request) {

		PostDto postDto = new PostDto(request.getTitle(), request.getContent());
		Long postId = postService.writePost(loginEmail, postDto);

		return new SuccessResponseResult(new WritePostResponse(postId));
	}

	@GetMapping("/{postId}")
	public SuccessResponseResult getPost(@LoginEmail String loginEmail, @Validated @PathVariable Long postId) {

		PostDetailDto post = postService.findOne(loginEmail, postId);

		return new SuccessResponseResult(new GetPostResponse(post));
	}

	@Data
	@AllArgsConstructor
	static class GetPostsResponse {
		private Long total;
		private List<PostSummaryDto> posts;
	}

	@Data
	static class WritePostRequest {
		@NotEmpty
		private String title;
		@NotEmpty
		private String content;
	}

	@Data
	@AllArgsConstructor
	static class WritePostResponse {
		private Long postId;
	}

	@Data
	@AllArgsConstructor
	static class GetPostResponse {
		private PostDetailDto postDetailDto;
	}
}
