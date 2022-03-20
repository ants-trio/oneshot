package happyhouse_team02.land.landservice.service;

import java.util.List;
import java.util.Optional;

import happyhouse_team02.land.landservice.domain.Member;

public interface MemberService {
	/**
	 * 회원 가입
	 */
	Long join(Member member);

	/**
	 * 회원 조회
	 */
	List<Member> findMembers();

	Long addBookmarkToMember(BookmarkDTO bookmark, String email);

	Long deleteBookmarkFromMember(Long bookmarkId, String email);

	Optional<Member> findOne(Long id);

	Optional<Member> findOne(String email);
}
