package happyhouse_team02.land.landservice.api;

import static happyhouse_team02.land.landservice.api.ApiMessage.*;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import happyhouse_team02.land.landservice.service.BookmarkDTO;
import happyhouse_team02.land.landservice.service.MemberService;
import happyhouse_team02.land.landservice.web.argumentresolver.LoginEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookmarkApiController {

	private final MemberService memberService;

	@PostMapping("/bookmark/new")
	public ResponseResult addBookmark(@LoginEmail String loginEmail,
									  @Validated @RequestBody AddBookmarkRequest request) {

		BookmarkDTO bookmarkDTO = new BookmarkDTO(request.getCity(), request.getRegion());
		Long bookmarkId = memberService.addBookmarkToMember(bookmarkDTO, loginEmail);
		log.info("bookmarkId={}", bookmarkId);
		return new ResponseResult(new AddBookmarkResponse(bookmarkId));
	}

	@DeleteMapping("/bookmark")
	public ResponseResult deleteBookmark(@LoginEmail String loginEmail,
										 @Validated @RequestBody DeleteBookmarkRequest request) {

		memberService.deleteBookmarkFromMember(request.getBookmarkId(), loginEmail);

		return new ResponseResult(new DeleteBookmarkResponse(SC_OK));
	}

	@Data
	@AllArgsConstructor
	static class ResponseResult<T> {
		private T data;
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
		private Long id;
	}

	@Data
	static class DeleteBookmarkRequest {
		@NotEmpty
		private Long bookmarkId;
	}

	@Data
	@AllArgsConstructor
	static class DeleteBookmarkResponse {
		private int message;
	}

}
