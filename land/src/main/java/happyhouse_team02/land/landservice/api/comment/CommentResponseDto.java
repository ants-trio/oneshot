package happyhouse_team02.land.landservice.api.comment;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.CommentRole;
import lombok.Data;

@Data
public class CommentResponseDto {
	private Long commentId;
	private String writer;
	private String content;
	private CommentRole role;
	private LocalDateTime createdDate;
	private Boolean isWriter = false;
	private List<CommentResponseDto> comments;

	public CommentResponseDto(Comment comment) {
		commentId = comment.getId();
		writer = comment.getMember().getEmail();
		content = comment.getContent();
		createdDate = comment.getCreatedDate();
		role = comment.getRole();
		comments = comment.getChildren().stream()
			.map(CommentResponseDto::new)
			.collect(toList());
		comments.forEach(c -> c.addRole(writer));
	}

	public void addRole(String email) {
		if (writer.equals(email)) {
			isWriter = true;
		}
	}
}
