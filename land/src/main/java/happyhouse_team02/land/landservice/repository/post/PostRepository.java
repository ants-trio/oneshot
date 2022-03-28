package happyhouse_team02.land.landservice.repository.post;

import java.util.List;
import java.util.Optional;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostRepository {

	Optional<Post> findById(Long id);
	List<Post> findAll(int start, int amount);
	Long countPosts();
	Long save(Post post);
}
