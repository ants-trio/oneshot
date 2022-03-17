package happyhouse_team02.land.landservice.repository;

import java.util.List;
import java.util.Optional;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberRepository {
	void save(Member member);

	Optional<Member> findById(Long id);

	Optional<Member> findByEmail(String name);

	List<Member> findAll();
}
