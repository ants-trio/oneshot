package happyhouse_team02.land.landservice.service.post;

import org.springframework.data.domain.Page;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostService {

	/**
	 * 게시글 리스트를 가져옵니다.
	 */
	Page<Post> findPostPages(int pageNo, int amount);

	/**
	 * 게시글 하나를 조회합니다.
	 */
	Post findOne(String email, Long postId);

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
