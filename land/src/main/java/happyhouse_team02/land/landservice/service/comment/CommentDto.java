package happyhouse_team02.land.landservice.service.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

	private Long postId;
	private Long commentId;
	private String content;

	public CommentDto (Long postId, String content){
		this.postId = postId;
		this.content = content;
	}
}
