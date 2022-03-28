package happyhouse_team02.land.landservice.api.bookmark;

import happyhouse_team02.land.landservice.api.bookmark.BookmarkApiController.AddBookmarkRequest;
import happyhouse_team02.land.landservice.service.bookmark.BookmarkDto;

public interface BookmarkValidator {

	/**
	 * request 의 데이터가 올바른지 검증합니다.
	 * 검증을 마친 BookmarkDto 를 반환합니다.
	 */
	BookmarkDto getValidatedDto(String loginEmail, AddBookmarkRequest bookmarkRequest);
}
