package happyhouse_team02.land.landservice.repository.post;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import happyhouse_team02.land.landservice.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

	private final EntityManager em;

	@Override
	public Optional<Post> findById(Long id) {
		return Optional.ofNullable(em.find(Post.class, id));
	}

	@Override
	public List<Post> findAll(int start, int amount) {

		return em.createQuery("select p from Post p join fetch p.member", Post.class)
			.setFirstResult(start)
			.setMaxResults(amount)
			.getResultList();
	}

	@Override
	public Long countPosts() {
		return em.createQuery("select count (p) from Post p", Long.class).getSingleResult();
	}

	@Override
	public Long save(Post post) {
		em.persist(post);
		return post.getId();
	}
}
