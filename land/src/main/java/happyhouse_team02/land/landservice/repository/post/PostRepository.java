package happyhouse_team02.land.landservice.repository.post;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	@EntityGraph(attributePaths = {"member"})
	Page<Post> findAll(Pageable pageable);

	@EntityGraph(attributePaths = {"member", "comments"})
	Optional<Post> findDistinctById(Long id);
	// TODO : 현재 나가는 쿼리는 1개, 근데 데이터뻥튀기가 되고 있다.
	// 최적화하려면 경로를 모두 써주는 방식으로 해야하므로 추후에 query dsl 문법으로 개선하자.
}
