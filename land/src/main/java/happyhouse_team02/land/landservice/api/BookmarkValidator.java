package happyhouse_team02.land.landservice.api;

import happyhouse_team02.land.landservice.api.BookmarkApiController.AddBookmarkRequest;
import happyhouse_team02.land.landservice.service.BookmarkDTO;

public interface BookmarkValidator {

	/**
	 * request의 데이터가 올바른지 검증합니다.
	 * 검증을 마친 BookmarkDTO를 반환합니다.
	 */
	BookmarkDTO getValidatedDTO(String loginEmail, AddBookmarkRequest bookmarkRequest);
}
