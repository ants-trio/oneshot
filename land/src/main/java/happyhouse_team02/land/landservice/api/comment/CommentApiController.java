package happyhouse_team02.land.landservice.api.comment;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.api.SuccessResponseResult;
import happyhouse_team02.land.landservice.service.comment.CommentDto;
import happyhouse_team02.land.landservice.service.comment.CommentService;
import happyhouse_team02.land.landservice.web.argumentresolver.LoginEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentApiController {

	private final CommentService commentService;


	@PostMapping("/new")
	public SuccessResponseResult writeComment(@LoginEmail String loginEmail,
											  @PathVariable Long postId,
											  @Validated @RequestBody WriteCommentRequest request) {
		CommentDto commentDto = new CommentDto(postId, request.getContent());
		Long commentId = commentService.writeComment(loginEmail, commentDto);

		return new SuccessResponseResult(new WriteCommentResponse(commentId));
	}

	@PatchMapping("/{commentId}")
	public SuccessResponseResult updateComment(@LoginEmail String loginEmail,
											   @PathVariable Long postId,
											   @Validated @PathVariable Long commentId) {

		return new SuccessResponseResult();
	}

	@DeleteMapping("/{commentId}")
	public SuccessResponseResult deleteComment(@LoginEmail String loginEmail,
											   @Validated @PathVariable Long postId,
											   @Validated @PathVariable Long commentId) {

		return new SuccessResponseResult();
	}

	@Data
	@AllArgsConstructor
	static class WriteCommentRequest {

		@NotEmpty
		private String content;
	}

	@Data
	@AllArgsConstructor
	static class WriteCommentResponse {
		private Long commentId;
	}
}
