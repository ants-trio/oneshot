package happyhouse_team02.land.landservice.service.bookmark;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.DuplicatedBookmarkException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkDtoValidatorImpl implements BookmarkDtoValidator {

	@Override
	public Bookmark getBookmark(Member member, BookmarkDto bookmarkDto) {
		validateDuplicated(member, bookmarkDto.getArea());
		return  Bookmark.createBookmark(member, bookmarkDto.getArea());
	}

	private void validateDuplicated(Member findMember, Area area) {
		findMember.getBookmarks()
			.stream()
			.filter(bookmark -> bookmark.getArea().equals(area))
			.findAny()
			.ifPresent(bookmark -> {
				throw new DuplicatedBookmarkException();
			});
	}
}
