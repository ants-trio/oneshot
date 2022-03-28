package happyhouse_team02.land.landservice.repository.member;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberValidatedRepository {

	void save(Member member);

	Member getMember(String email);

	Member getMember(Long id);

	List<Member> getMembers();
}
