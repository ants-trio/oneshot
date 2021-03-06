package happyhouse_team02.land.landservice.api.bookmark;

import javax.validation.constraints.NotEmpty;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class BookmarkResponseDto {

	private Long bookmarkId;

	@NotEmpty
	private Area area;

	public BookmarkResponseDto(Bookmark bookmark) {
		this.bookmarkId = bookmark.getId();
		this.area = bookmark.getArea();
	}
}
