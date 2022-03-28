package happyhouse_team02.land.landservice.repository.member;

import java.util.List;
import java.util.Optional;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberRepository {
	void save(Member member);

	Optional<Member> findById(Long id);

	Optional<Member> findByEmail(String email);

	List<Member> findAll();
}
