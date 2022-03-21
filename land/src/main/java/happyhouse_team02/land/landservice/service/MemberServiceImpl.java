package happyhouse_team02.land.landservice.service;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Bookmark;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.exception.NoSuchBookmarkException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

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
	public Optional<Member> findOne(String email) {
		return memberRepository.findByEmail(email);
	}

	@Override
	public Long addBookmarkToMember(BookmarkDTO bookmarkDTO, String email) {
		Member findMember = getMember(email);

		Bookmark bookmark = new Bookmark(findMember, bookmarkDTO.getArea());
		memberRepository.save(findMember);

		return bookmark.getId();
	}

	@Override
	public Long deleteBookmarkFromMember(Long bookmarkId, String email) {
		Member findMember = getMember(email);

		Bookmark deleteBookmark = findMember.getBookmarks()
			.stream()
			.filter(bookmark -> bookmark.getId().equals(bookmarkId))
			.findAny()
			.orElseThrow(() -> new NoSuchBookmarkException(NO_SUCH_BOOKMARK_MASSAGE));

		findMember.deleteBookmark(deleteBookmark);
		return bookmarkId;
	}

	@Override
	public List<BookmarkDTO> getBookmarksFromMember(String email) {
		return getMember(email).getBookmarks()
			.stream()
			.map(BookmarkDTO::new)
			.collect(toList());
	}

	private Member getMember(String email) {
		return findOne(email).orElseThrow(() -> new NoSuchMemberException(NO_SUCH_MEMBER_MASSAGE));
	}
}