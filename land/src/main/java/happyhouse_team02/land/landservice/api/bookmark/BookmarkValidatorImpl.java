package happyhouse_team02.land.landservice.api.bookmark;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.DuplicatedBookmarkException;
import happyhouse_team02.land.landservice.service.bookmark.BookmarkDto;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookmarkValidatorImpl implements BookmarkValidator{

	private final MemberService memberService;

	@Override
	public BookmarkDto getValidatedDto(String loginEmail, BookmarkApiController.AddBookmarkRequest bookmarkRequest) {
		BookmarkDto bookmarkDTO = new BookmarkDto(bookmarkRequest.getCity(), bookmarkRequest.getRegion());
		Member findMember = memberService.findOne(loginEmail);

		validateDuplicated(findMember, bookmarkDTO.getArea());
		return bookmarkDTO;
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
