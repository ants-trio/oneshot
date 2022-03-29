package happyhouse_team02.land.landservice.service.comment;

import java.time.LocalDateTime;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Role;
import lombok.Data;

@Data
public class CommentDto {

	private String writer;
	private String content;
	private LocalDateTime createdDate;
	private Role role = Role.NORMAL;

	public CommentDto (Comment comment){
		writer = comment.getMember().getEmail();
		content = comment.getContent();
		createdDate = comment.getCreatedDate();
	}
}
