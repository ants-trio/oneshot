package happyhouse_team02.land.landservice.service.post;

import java.util.List;

public interface PostService {

	List<PostSummaryDto> findPostsSummary(int pageNo, int amount);

	Long countPosts();

	PostDetailDto findOne(String email, Long postId);

	Long writePost(String loginEmail, PostDto postDto);
}
