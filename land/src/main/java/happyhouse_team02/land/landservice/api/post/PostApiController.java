package happyhouse_team02.land.landservice.api.post;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.api.SuccessResponseResult;
import happyhouse_team02.land.landservice.service.PostService;
import happyhouse_team02.land.landservice.service.PostSummaryDto;
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
	private final PostValidator validator;

	@GetMapping
	public SuccessResponseResult getPosts(@Validated @RequestBody GetPostsRequest request) {
		log.info("request={}", request);
		Long total = postService.countPosts();
		List<PostSummaryDto> postsSummary = postService.findPostsSummary(request.getPageNo(), request.getAmount());

		return new SuccessResponseResult(new GetPostsResponse(total, postsSummary));
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
}
