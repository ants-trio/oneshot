package happyhouse_team02.land.landservice.service.bookmark;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.bookmark.BookmarkValidatedRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

	private final MemberService memberService;
	private final BookmarkValidatedRepository bookmarkRepository;

	@Override
	public List<BookmarkDto> findBookmarks(String email) {
		Member findMember = memberService.findOne(email);
		return findMember.getBookmarks().stream().map(BookmarkDto::new).collect(toList());
	}

	@Override
	@Transactional
	public Long addBookmark(String email, BookmarkDto bookmarkDto) {
		Member findMember = memberService.findOne(email);
		Bookmark bookmark = getBookmark(findMember, bookmarkDto);
		return bookmarkRepository.save(bookmark);
	}

	private Bookmark getBookmark(Member findMember, BookmarkDto bookmarkDto) {
		return Bookmark.createBookmark(findMember, bookmarkDto.getArea());
	}
}
