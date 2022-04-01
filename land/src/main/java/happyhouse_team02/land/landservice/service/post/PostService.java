package happyhouse_team02.land.landservice.service.post;

import org.springframework.data.domain.Page;

public interface PostService {

	/**
	 * 게시글 리스트를 가져옵니다.
	 */
	Page<PostSummaryDto> findPostsSummary(int pageNo, int amount);

	/**
	 * 게시글 하나를 조회합니다.
	 */
	PostDetailDto findOne(String email, Long postId);

	/**
	 * 게시글을 작성합니다.
	 */
	Long writePost(String loginEmail, PostDto postDto);
}
