package happyhouse_team02.land.landservice.service.member;

import java.util.List;
import java.util.Optional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.bookmark.BookmarkDto;

public interface MemberService {

	/**
	 * 회원 조회
	 */
	Member findOne(String email);

	/**
	 * 회원 가입
	 */
	Long join(Member member);

	/**
	 * 회원 조회
	 */
	List<Member> findMembers();


	Optional<Member> findOne(Long id);

	Long addBookmarkToMember(BookmarkDto bookmark, String email);

	Long deleteBookmarkFromMember(Long bookmarkId, String email);

	List<BookmarkDto> getBookmarksFromMember(String email);
	



}
