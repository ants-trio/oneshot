package happyhouse_team02.land.landservice.service.comment;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Comment;

public interface CommentService {

	/**
	 * 특정 게시글의 댓글을 작성합니다.
	 */
	Long writeComment(Comment comment);

	/**
	 * 특정 게시글의 댓글을 조회합니다.
	 */
	List<Comment> findComments(Long postId);
}
