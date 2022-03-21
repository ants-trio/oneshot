package happyhouse_team02.land.landservice.api;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.DuplicatedBookmarkException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.service.BookmarkDTO;
import happyhouse_team02.land.landservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookmarkValidatorImpl implements BookmarkValidator{

	private final MemberService memberService;

	@Override
	public BookmarkDTO getValidatedDTO(String loginEmail, BookmarkApiController.AddBookmarkRequest bookmarkRequest) {
		BookmarkDTO bookmarkDTO = new BookmarkDTO(bookmarkRequest.getCity(), bookmarkRequest.getRegion());
		Member findMember = getMember(loginEmail);

		validateDuplicated(findMember, bookmarkDTO.getArea());
		return bookmarkDTO;
	}

	private Member getMember(String email) {
		return memberService.findOne(email).orElseThrow(() -> new NoSuchMemberException(NO_SUCH_MEMBER_MASSAGE));
	}

	private void validateDuplicated(Member findMember, Area area) {
		findMember.getBookmarks()
			.stream()
			.filter(bookmark -> bookmark.getArea().equals(area))
			.findAny()
			.ifPresent(bookmark -> {
				throw new DuplicatedBookmarkException(DUPLICATED_BOOKMARK_MASSAGE);
			});
	}
}
