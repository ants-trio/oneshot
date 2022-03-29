package happyhouse_team02.land.landservice.repository.comment;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Comment;

public interface CommentRepository {

	public List<Comment> findByPostId(Long id);
}
