package happyhouse_team02.land.landservice.service.bookmark;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

	private final MemberService memberService;

	@Override
	public List<BookmarkDto> findBookmarks(String email) {
		Member findMember = memberService.findOne(email);
		return findMember.getBookmarks().stream().map(BookmarkDto::new).collect(toList());
	}
}
