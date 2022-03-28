package happyhouse_team02.land.landservice.service.bookmark;

import java.util.List;

public interface BookmarkService {

	List<BookmarkDto> findBookmarks(String email);

	Long addBookmark(String email, BookmarkDto bookmarkDto);
}
