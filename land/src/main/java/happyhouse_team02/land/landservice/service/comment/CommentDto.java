package happyhouse_team02.land.landservice.service.comment;

import lombok.Data;

@Data
public class CommentDto {

	private Long commentId;
	private Long postId;
	private String content;

	public CommentDto (Long postId, String content){
		this.postId = postId;
		this.content = content;
	}
}
