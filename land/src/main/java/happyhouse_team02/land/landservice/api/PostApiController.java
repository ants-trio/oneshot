package happyhouse_team02.land.landservice.api;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
		Page<PostSummaryDto> postsSummary = postService.findPostsSummary(pageNo, amount);

		return new SuccessResponseResult(new GetPostsResponse(postsSummary));
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
		PostDetailDto post = postService.findDetailOne(loginEmail, postId);

		return new SuccessResponseResult(new GetPostResponse(post));
	}

	@PatchMapping("/{postId}")
	public SuccessResponseResult updatePost(@LoginEmail String loginEmail,
											@Validated @PathVariable Long postId,
											@Validated @RequestBody UpdatePostRequest request) {
		PostDto postDto = new PostDto(postId, request.getTitle(), request.getContent());
		postService.updatePost(loginEmail, postDto);

		return new SuccessResponseResult();
	}

	@DeleteMapping("/{postId}")
	public SuccessResponseResult deletePost(@LoginEmail String loginEmail,
											@Validated @PathVariable Long postId) {

		postService.deletePost(loginEmail, postId);
		return new SuccessResponseResult();
	}

	@Data
	@AllArgsConstructor
	static class GetPostsResponse {
		private Page<PostSummaryDto> posts;
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

	@Data
	static class UpdatePostRequest {
		@NotEmpty
		private String title;
		@NotEmpty
		private String content;
	}
}
