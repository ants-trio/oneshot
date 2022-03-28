package happyhouse_team02.land.landservice.service.member;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.bookmark.BookmarkDto;

public interface MemberService {

	/**
	 * 회원 조회
	 */
	Member findOne(Long id);
	Member findOne(String email);

	/**
	 * 회원 가입
	 */
	Long join(Member member);

	/**
	 * 회원 조회
	 */
	List<Member> findMembers();




	Long addBookmarkToMember(BookmarkDto bookmark, String email);

	void deleteBookmarkFromMember(Long bookmarkId, String email);

	List<BookmarkDto> getBookmarksFromMember(String email);
	



}
