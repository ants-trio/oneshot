package happyhouse_team02.land.landservice.service.comment;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Comment;

public interface CommentService {

	/**
	 * 특정 댓글을 조회합니다.
	 */
	Comment findOne(Long commentId);

	/**
	 * 특정 게시글의 댓글을 작성합니다.
	 */
	Long writeComment(String email, CommentDto commentDto);

	/**
	 * 특정 게시글의 댓글들을 조회합니다.
	 */
	List<Comment> findComments(Long postId);

	/**
	 * 특정 게시글의 댓글을 수정합니다.
	 */
	void updateComment(String email, CommentDto commentDto);

	/**
	 * 특정 게시글의 댓글을 삭제합니다.
	 */
	void deleteComment(String email, Long commentId);
}
