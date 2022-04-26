package happyhouse_team02.land.landservice.api.comment;

import static java.util.stream.Collectors.*;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.api.SuccessResponseResult;
import happyhouse_team02.land.landservice.service.comment.CommentDto;
import happyhouse_team02.land.landservice.service.comment.CommentService;
import happyhouse_team02.land.landservice.web.util.argumentresolver.LoginEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentApiController {

	private final CommentService commentService;

	@GetMapping
	public SuccessResponseResult getComments(@LoginEmail String loginEmail, @PathVariable Long postId) {

		List<CommentResponseDto> comments = commentService.findComments(postId)
			.stream()
			.map(CommentResponseDto::new)
			.collect(toList());
		comments.forEach(comment -> comment.addRole(loginEmail));

		return new SuccessResponseResult(new GetCommentsResponse(comments));
	}

	@PostMapping("/new")
	public SuccessResponseResult writeComment(@LoginEmail String loginEmail, @PathVariable Long postId,
											  @Validated @RequestBody WriteCommentRequest request) {
		CommentDto commentDto = new CommentDto(postId, request.getContent());
		Long commentId = commentService.writeComment(loginEmail, commentDto);

		return new SuccessResponseResult(new WriteCommentResponse(commentId));
	}

	@PatchMapping("/{commentId}")
	public SuccessResponseResult updateComment(@LoginEmail String loginEmail,
											   @PathVariable Long postId,
											   @PathVariable Long commentId,
											   @Validated @RequestBody UpdateCommentRequest request) {
		CommentDto commentDto = new CommentDto(postId, commentId, request.getContent());
		commentService.updateComment(loginEmail, commentDto);

		return new SuccessResponseResult();
	}

	@DeleteMapping("/{commentId}")
	public SuccessResponseResult deleteComment(@LoginEmail String loginEmail, @PathVariable Long postId,
											   @PathVariable Long commentId) {
		commentService.deleteComment(loginEmail, commentId);

		return new SuccessResponseResult();
	}

	@PostMapping("/{commentId}/new")
	public SuccessResponseResult addComment(@LoginEmail String loginEmail,
											@PathVariable Long postId,
											@PathVariable Long commentId,
											@Validated @RequestBody WriteCommentRequest request){
		CommentDto commentDto = new CommentDto(postId, commentId, request.getContent());
		Long comment = commentService.addComment(loginEmail, commentDto);
		return new SuccessResponseResult(new AddCommentResponse(comment));
	}

	@Data
	@AllArgsConstructor
	static class GetCommentsResponse {
		private List<CommentResponseDto> comments;
	}

	@Data
	static class WriteCommentRequest {
		@NotEmpty
		private String content;
	}

	@Data
	@AllArgsConstructor
	static class WriteCommentResponse {
		private Long commentId;
	}

	@Data
	static class UpdateCommentRequest {
		@NotEmpty
		private String content;
	}

	@Data
	@AllArgsConstructor
	static class AddCommentResponse {
		private Long commentId;
	}

}
