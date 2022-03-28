package happyhouse_team02.land.landservice.service.bookmark;

import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;

public interface BookmarkDtoValidator {

	/**
	 * BookmarkDto 를 추가할 수 있는지 검증합니다.
	 * 검증되면 Bookmark 로 변환해줍니다.
	 */
	Bookmark getBookmark(Member member, BookmarkDto bookmarkDto);
}
