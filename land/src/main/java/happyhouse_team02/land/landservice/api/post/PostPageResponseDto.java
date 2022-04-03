package happyhouse_team02.land.landservice.api.post;

import java.time.LocalDateTime;

import happyhouse_team02.land.landservice.domain.Post;
import lombok.Data;

@Data
public class PostPageResponseDto {

	private Long id;
	private String title;
	private String writer;
	private LocalDateTime createdDate;

	public PostPageResponseDto(Post post) {
		id = post.getId();
		title = post.getTitle();
		writer = post.getMember().getEmail();
		createdDate = post.getCreatedDate();
	}
}
