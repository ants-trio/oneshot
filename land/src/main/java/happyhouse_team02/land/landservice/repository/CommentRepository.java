package happyhouse_team02.land.landservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@EntityGraph(attributePaths = {"member", "post", "parent"})
	Optional<Comment> findById(Long id);

	@EntityGraph(attributePaths = {"member", "children"})
	List<Comment> findByPostIdOrderByCreatedDate(Long postId);
}
