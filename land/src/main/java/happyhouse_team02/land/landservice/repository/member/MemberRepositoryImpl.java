package happyhouse_team02.land.landservice.repository.member;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import happyhouse_team02.land.landservice.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

	private final EntityManager em;

	@Override
	public void save(Member member) {
		em.persist(member);
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(em.find(Member.class, id));
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		return em.createQuery("select m from Member m where m.email = :email", Member.class)
			.setParameter("email", email)
			.getResultStream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member as m", Member.class)
			.getResultList();
	}
}
