package happyhouse_team02.land.landservice.service.member;

import java.util.List;

import happyhouse_team02.land.landservice.service.comment.CommentDto;
import happyhouse_team02.land.landservice.service.post.PostDto;
import lombok.Data;

@Data
public class MemberDto {

	private String email;

	private List<PostDto> posts;

	private List<CommentDto> comments;
}
