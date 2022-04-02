package happyhouse_team02.land.landservice.api.post;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import happyhouse_team02.land.landservice.api.comment.CommentResponseDto;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponseDto {
	private Long id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private List<CommentResponseDto> comments;
	private Role role = Role.NORMAL;

	public PostResponseDto(Post post) {
		writer = post.getMember().getEmail();
		id = post.getId();
		title = post.getTitle();
		content = post.getContent();
		createdDate = post.getCreatedDate();
		comments = post.getComments().stream().map(CommentResponseDto::new).collect(toList());
	}
}
