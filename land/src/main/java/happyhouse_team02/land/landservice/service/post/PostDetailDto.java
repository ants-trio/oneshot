package happyhouse_team02.land.landservice.service.post;

import java.time.LocalDateTime;
import java.util.List;

import happyhouse_team02.land.landservice.service.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDetailDto {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private List<CommentDto> comments;
}
