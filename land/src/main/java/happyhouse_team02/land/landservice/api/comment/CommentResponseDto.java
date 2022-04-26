package happyhouse_team02.land.landservice.api.comment;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Role;
import lombok.Data;

@Data
public class CommentResponseDto {
	private Long commentId;
	private String writer;
	private String content;
	private boolean parent = true;
	private LocalDateTime createdDate;
	private Role role = Role.NORMAL;
	private List<CommentResponseDto> comments;

	public CommentResponseDto(Comment comment) {
		commentId = comment.getId();
		writer = comment.getMember().getEmail();
		content = comment.getContent();
		createdDate = comment.getCreatedDate();
		comments = comment.getChildren().stream()
			.map(CommentResponseDto::new)
			.collect(toList());

		if(comment.getParent() != null){
			parent = false;
		}
	}

	public void addRole(String email) {
		if (writer.equals(email)) {
			role = Role.WRITER;
		}
	}
}
