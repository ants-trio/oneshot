package happyhouse_team02.land.landservice.repository.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(Long id);
}
