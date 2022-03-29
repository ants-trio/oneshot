package happyhouse_team02.land.landservice.repository.comment;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Comment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

	private final EntityManager em;

	@Override
	public List<Comment> findByPostId(Long id) {
		return em.createQuery("select c from Comment c join fetch c.member where c.post.id =: id", Comment.class)
			.setParameter("id", id)
			.getResultList();
	}
}
