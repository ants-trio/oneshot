package happyhouse_team02.land.landservice.repository.post;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostRepository {
	List<Post> findAll(int start, int amount);
	Long countPosts();
	Long save(Post post);
}