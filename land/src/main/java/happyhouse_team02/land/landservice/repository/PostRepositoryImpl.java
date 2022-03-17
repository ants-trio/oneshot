package happyhouse_team02.land.landservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import happyhouse_team02.land.landservice.domain.Post;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{

	private final EntityManager em;

	@Override
	public List<Post> findAll() {
		return null;
	}
}
