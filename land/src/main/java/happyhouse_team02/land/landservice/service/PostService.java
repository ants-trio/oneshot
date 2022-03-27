package happyhouse_team02.land.landservice.service;

import java.util.List;

public interface PostService {

	List<PostSummaryDto> findPostsSummary(int pageNo, int amount);

	Long countPosts();

	PostDto findOne();

	Long writePost(String loginEmail, PostDto postDto);
}
