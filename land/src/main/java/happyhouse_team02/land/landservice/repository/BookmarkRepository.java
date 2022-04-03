package happyhouse_team02.land.landservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
