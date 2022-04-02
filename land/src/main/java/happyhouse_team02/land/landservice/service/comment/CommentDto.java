package happyhouse_team02.land.landservice.service.comment;

import java.time.LocalDateTime;

import happyhouse_team02.land.landservice.domain.Role;
import lombok.Data;

@Data
public class CommentDto {

	private Long commentId;
	private String writer;
	private String content;
	private LocalDateTime createdDate;
	private Role role = Role.NORMAL;

}
