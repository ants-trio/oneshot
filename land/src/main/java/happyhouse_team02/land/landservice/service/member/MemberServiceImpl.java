package happyhouse_team02.land.landservice.service.member;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.BookmarkRepository;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import happyhouse_team02.land.landservice.service.bookmark.BookmarkDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final BookmarkRepository bookmarkRepository;
	private final MemberValidator memberValidator;

	@Override
	public Member findOne(String email) {
		return memberValidator.getMember(email);
	}

	@Transactional
	@Override
	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}

	@Override
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Optional<Member> findOne(Long id) {
		return memberRepository.findById(id);
	}


	@Override
	@Transactional
	public Long addBookmarkToMember(BookmarkDto bookmarkDTO, String email) {
		Member findMember = findOne(email);
		Bookmark bookmark = new Bookmark(findMember, bookmarkDTO.getArea());

		bookmarkRepository.save(bookmark);
		return bookmark.getId();
	}

	@Override
	@Transactional
	public Long deleteBookmarkFromMember(Long bookmarkId, String email) {
		Member findMember = findOne(email);

		Bookmark deleteBookmark = findMember.getBookmarks()
			.stream()
			.filter(bookmark -> bookmark.getId().equals(bookmarkId))
			.findAny()
			.orElseThrow(NoSuchMemberException::new);

		findMember.deleteBookmark(deleteBookmark);
		return bookmarkId;
	}

	@Override
	public List<BookmarkDto> getBookmarksFromMember(String email) {
		return findOne(email).getBookmarks().stream().map(BookmarkDto::new).collect(toList());
	}
}