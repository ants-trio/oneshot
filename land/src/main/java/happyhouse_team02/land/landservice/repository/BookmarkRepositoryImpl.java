package happyhouse_team02.land.landservice.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import happyhouse_team02.land.landservice.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository{

	private final EntityManager em;

	@Override
	public void save(Bookmark bookmark) {
		em.persist(bookmark);
	}
}
