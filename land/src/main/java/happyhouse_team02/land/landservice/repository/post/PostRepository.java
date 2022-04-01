package happyhouse_team02.land.landservice.repository.post;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	@EntityGraph(attributePaths = {"member", "comments"})
	Optional<Post> findById(Long id);

}
