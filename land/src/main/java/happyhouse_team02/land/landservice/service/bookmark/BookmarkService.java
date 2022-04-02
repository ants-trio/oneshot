package happyhouse_team02.land.landservice.service.bookmark;

import java.util.List;

import happyhouse_team02.land.landservice.domain.Bookmark;

public interface BookmarkService {
	/**
	 * 아이디로 북마크들을 조회합니다.
	 */
	List<Bookmark> findBookmarks(String email);

	/**
	 * 북마크를 추가합니다.
	 */
	Long addBookmark(String email, BookmarkDto bookmarkDto);

	/**
	 * 북마크를 제거합니다.
	 */
	void deleteBookmark(String email, Long bookmarkId);
}
