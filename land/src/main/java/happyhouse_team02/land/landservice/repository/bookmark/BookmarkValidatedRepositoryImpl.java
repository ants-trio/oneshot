package happyhouse_team02.land.landservice.repository.bookmark;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Bookmark;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkValidatedRepositoryImpl implements BookmarkValidatedRepository{

	private final BookmarkRepository bookmarkRepository;

	@Override
	public Long save(Bookmark bookmark) {
		return bookmarkRepository.save(bookmark);
	}
}
