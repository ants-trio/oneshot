package happyhouse_team02.land.landservice.service.member;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.repository.bookmark.BookmarkRepository;
import happyhouse_team02.land.landservice.repository.member.MemberValidatedRepository;
import happyhouse_team02.land.landservice.service.bookmark.BookmarkDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final BookmarkRepository bookmarkRepository;
	private final MemberValidatedRepository memberRepository;

	@Override
	public MemberDto findOne(String email) {
		return null;
	}

	@Override
	public MemberDto findOne(Long id) {
		return null;
	}

	@Transactional
	@Override
	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}

	@Override
	public List<MemberDto> findMembers() {
		return null;
	}

	@Override
	@Transactional
	public Long addBookmarkToMember(BookmarkDto bookmarkDTO, String email) {
		Member findMember = memberRepository.getMember(email);
		Bookmark bookmark = Bookmark.createBookmark(findMember, bookmarkDTO.getArea());
		bookmarkRepository.save(bookmark);
		return bookmark.getId();
	}

	@Override
	@Transactional
	public void deleteBookmarkFromMember(Long bookmarkId, String email) {
		Member findMember = memberRepository.getMember(email);
		findMember.getBookmarks().removeIf(bookmark -> bookmark.getId().equals(bookmarkId));
	}

	@Override
	public List<BookmarkDto> getBookmarksFromMember(String email) {
		return memberRepository.getMember(email).getBookmarks().stream().map(BookmarkDto::new).collect(toList());
	}
}