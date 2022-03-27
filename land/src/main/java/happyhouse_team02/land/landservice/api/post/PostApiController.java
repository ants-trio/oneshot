package happyhouse_team02.land.landservice.api.post;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.api.SuccessResponseResult;
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
	public SuccessResponseResult getPosts(@Validated @RequestBody GetPostsRequest request) {
		log.info("request={}", request);
		Long total = postService.countPosts();
		List<PostSummaryDto> postsSummary = postService.findPostsSummary(request.getPageNo(), request.getAmount());

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
	public SuccessResponseResult getPost(@LoginEmail String loginEmail, @Validated @PathVariable int postId) {

		PostDto post = postService.findOne();
		log.info("loginEmail={}", loginEmail);
		log.info("postId={}", postId);
		return new SuccessResponseResult();
	}

	@Data
	static class GetPostsRequest {
		@NotNull
		private int pageNo;
		@NotNull
		private int amount;
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
}
