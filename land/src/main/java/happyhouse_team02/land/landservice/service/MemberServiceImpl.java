package happyhouse_team02.land.landservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}

	@Override
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Long addBookmarkToMember(BookmarkDTO bookmarkDTO, String email) {
		return null;
	}

	@Override
	public Long deleteBookmarkFromMember(Long bookmarkId, String email) {
		return null;
	}

	@Override
	public Optional<Member> findOne(Long id) {
		return memberRepository.findById(id);
	}

	@Override
	public Optional<Member> findOne(String email) {
		return memberRepository.findByEmail(email);
	}

}