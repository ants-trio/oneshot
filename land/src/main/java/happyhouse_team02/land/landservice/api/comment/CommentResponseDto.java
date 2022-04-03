package happyhouse_team02.land.landservice.api.comment;

import java.time.LocalDateTime;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Role;
import lombok.Data;

@Data
public class CommentResponseDto {
	private Long commentId;
	private String writer;
	private String content;
	private LocalDateTime createdDate;
	private Role role = Role.NORMAL;

	public CommentResponseDto(Comment comment) {
		commentId = comment.getId();
		writer = comment.getMember().getEmail();
		content = comment.getContent();
		createdDate = comment.getCreatedDate();
	}

	public void addRole(String email) {
		if (writer.equals(email)) {
			role = Role.WRITER;
		}
	}
}
