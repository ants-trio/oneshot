package happyhouse_team02.land.landservice.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostService {

	/**
	 * 게시글 리스트를 가져옵니다.
	 */
	Page<Post> findPostPages(Pageable pageable);

	/**
	 * 게시글 하나를 댓글과 함께 조회합니다.
	 * TODO 쿼리 수정이 필요. 댓글을 모두 조회하면서 댓글의 Member까지 페치조인 해야함.
	 */
	Post findOneWithComment(Long postId);

	/**
	 * 게시글 하나를 조회합니다.
	 */
	Post findOne(Long postId);

	/**
	 * 게시글을 작성합니다.
	 */
	Long writePost(String loginEmail, PostDto postDto);

	/**
	 * 게시글을 수정합니다.
	 */
	void updatePost(String loginEmail, PostDto postDto);

	/**
	 * 게시글을 삭제합니다.
	 */
	void deletePost(String loginEmail, Long postId);
}
