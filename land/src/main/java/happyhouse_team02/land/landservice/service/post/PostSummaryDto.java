package happyhouse_team02.land.landservice.service.post;

import java.time.LocalDateTime;

import happyhouse_team02.land.landservice.domain.Post;
import lombok.Data;

@Data
public class PostSummaryDto {

	private Long id;
	private String title;
	private String writer;
	private LocalDateTime createdDate;

	public PostSummaryDto(Post post) {
		id = post.getId();
		title = post.getTitle();
		writer = post.getMember().getEmail();
		createdDate = post.getCreatedDate();
	}
}
