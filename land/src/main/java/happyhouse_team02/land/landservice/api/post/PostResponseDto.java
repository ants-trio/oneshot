package happyhouse_team02.land.landservice.api.post;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import happyhouse_team02.land.landservice.api.comment.CommentResponseDto;
import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Post;
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
	private Boolean isWriter = false;

	public PostResponseDto(Post post, List<Comment> comments) {
		writer = post.getMember().getEmail();
		id = post.getId();
		title = post.getTitle();
		content = post.getContent();
		createdDate = post.getCreatedDate();
		this.comments = comments.stream().map(CommentResponseDto::new).collect(toList());
	}

	public void addRole(String email) {
		if (writer.equals(email)) {
			isWriter = true;
		}
		comments.forEach(comment -> comment.addRole(email));
	}
}
