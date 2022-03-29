package happyhouse_team02.land.landservice.service.post;

import java.time.LocalDateTime;
import java.util.List;

import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.domain.Role;
import happyhouse_team02.land.landservice.service.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDetailDto {
	private Long id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private List<CommentDto> comments;
	private Role role = Role.NORMAL;

	public PostDetailDto(Post post) {
		writer = post.getMember().getEmail();
		id = post.getId();
		title = post.getTitle();
		content = post.getContent();
		createdDate = post.getCreatedDate();
	}
}
