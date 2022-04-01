package happyhouse_team02.land.landservice.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(String email);
}
