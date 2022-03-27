package happyhouse_team02.land.landservice.repository;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostRepository {
	List<Post> findAll(int start, int amount);
	Long countPosts();
}
