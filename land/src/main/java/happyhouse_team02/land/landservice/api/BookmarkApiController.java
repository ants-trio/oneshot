package happyhouse_team02.land.landservice.api;

import static java.util.stream.Collectors.*;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.ExceptionMessage;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookmarkApiController {

	private final MemberService memberService;

	@GetMapping("/bookmarks")
	public ResponseResult getBookmarks(@PathVariable Long memberId) {
		// TODO Member 검증해야함
		Member findMember = memberService.findOne(memberId).get();

		List<BookmarkDto> bookmarks = findMember.getBookmarks().stream()
			.map(Bookmark::getArea)
			.map(BookmarkDto::new)
			.collect(toList());

		return new ResponseResult(bookmarks);
	}

	@PostMapping("/bookmark/new")
	public ResponseResult saveBookmark(@Validated @RequestBody BookMarkForm bookMarkForm) {
		Member findMember = memberService.findOne(bookMarkForm.getEmail())
			.orElseThrow(() -> new NoSuchMemberException(ExceptionMessage.NO_SUCH_MEMBER_MASSAGE));

		Area area = new Area(bookMarkForm.getCity(), bookMarkForm.getRegion());
		addBookmarkToMember(area, findMember);

		BookmarkDto bookmark = new BookmarkDto(area);
		return new ResponseResult(bookmark);
	}

	private void addBookmarkToMember(Area area, Member member) {
		Bookmark bookmark = new Bookmark(member, area);
	}

	@Data
	@AllArgsConstructor
	static class ResponseResult<T> {
		private T data;
	}

	@Data
	@AllArgsConstructor
	static class BookmarkDto {
		@NotEmpty
		Area area;
	}

}
