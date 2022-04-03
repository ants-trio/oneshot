package happyhouse_team02.land.landservice.repository.comment;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@EntityGraph(attributePaths = {"member"})
	List<Comment> findByPostId(Long id);
}
