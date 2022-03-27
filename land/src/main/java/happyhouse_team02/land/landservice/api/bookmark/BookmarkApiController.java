package happyhouse_team02.land.landservice.api.bookmark;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.api.SuccessResponseResult;
import happyhouse_team02.land.landservice.service.BookmarkDto;
import happyhouse_team02.land.landservice.service.MemberService;
import happyhouse_team02.land.landservice.web.argumentresolver.LoginEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkApiController {

	private final MemberService memberService;
	private final BookmarkValidator bookmarkValidator;

	@GetMapping
	public SuccessResponseResult getBookmarks(@LoginEmail String loginEmail) {

		List<BookmarkDto> bookmarks = memberService.getBookmarksFromMember(loginEmail);

		return new SuccessResponseResult(new GetBookmarkResponse(bookmarks));
	}


	@PostMapping("/new")
	public SuccessResponseResult addBookmark(@LoginEmail String loginEmail,
											 @Validated @RequestBody AddBookmarkRequest bookmarkRequest) {

		BookmarkDto bookmarkDTO = bookmarkValidator.getValidatedDTO(loginEmail, bookmarkRequest);
		Long bookmarkId = memberService.addBookmarkToMember(bookmarkDTO, loginEmail);

		return new SuccessResponseResult(new AddBookmarkResponse(bookmarkId));
	}

	@DeleteMapping
	public SuccessResponseResult deleteBookmark(@LoginEmail String loginEmail,
										 		@Validated @RequestBody DeleteBookmarkRequest request) {

		memberService.deleteBookmarkFromMember(request.getBookmarkId(), loginEmail);

		return new SuccessResponseResult();
	}

	@Data
	@AllArgsConstructor
	static class GetBookmarkResponse {
		private List<BookmarkDto> bookmarks;
	}

	@Data
	static class AddBookmarkRequest {
		@NotEmpty
		private String city;

		@NotEmpty
		private String region;
	}

	@Data
	@AllArgsConstructor
	static class AddBookmarkResponse {
		private Long bookmarkId;
	}

	@Data
	static class DeleteBookmarkRequest {
		@NotNull
		private Long bookmarkId;
	}


}
