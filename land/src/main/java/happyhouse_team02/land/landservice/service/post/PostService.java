package happyhouse_team02.land.landservice.service.post;

import java.util.List;

public interface PostService {

	List<PostSummaryDto> findPostsSummary(int pageNo, int amount);

	Long countPosts();

	PostDto findOne();

	Long writePost(String loginEmail, PostDto postDto);
}
