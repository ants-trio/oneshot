package happyhouse_team02.land.landservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	List<Bookmark> findAllByMember(Member member);
}
