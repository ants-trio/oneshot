package happyhouse_team02.land.landservice.service.bookmark;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.DuplicatedBookmarkException;
import happyhouse_team02.land.landservice.repository.BookmarkRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

	private final MemberService memberService;
	private final BookmarkRepository bookmarkRepository;

	@Override
	@Transactional
	public Long addBookmark(String email, BookmarkDto bookmarkDto) {
		Member findMember = memberService.findOne(email);
		Bookmark bookmark = validateBookmarkDto(findMember, bookmarkDto);
		return bookmarkRepository.save(bookmark).getId();
	}

	@Override
	@Transactional
	public void deleteBookmark(String email, Long bookmarkId) {
		Member findMember = memberService.findOne(email);
		findMember.getBookmarks().removeIf(bookmark -> bookmark.getId().equals(bookmarkId));
	}

	@Override
	public List<Bookmark> findBookmarks(String email) {
		Member findMember = memberService.findOne(email);
		return findMember.getBookmarks();
	}


	private Bookmark validateBookmarkDto(Member member, BookmarkDto bookmarkDto) {
		validateDuplicated(member, bookmarkDto.getArea());
		return Bookmark.createBookmark(member, bookmarkDto.getArea());
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
