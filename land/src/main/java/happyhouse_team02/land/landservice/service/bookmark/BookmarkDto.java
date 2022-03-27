package happyhouse_team02.land.landservice.service.bookmark;

import javax.validation.constraints.NotEmpty;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkDto {

	private Long bookmarkId;

	@NotEmpty
	private Area area;

	public BookmarkDto(String city, String region) {
		this.area = new Area(city, region);
	}

	public BookmarkDto(Bookmark bookmark) {
		this.bookmarkId = bookmark.getId();
		this.area = bookmark.getArea();
	}
}
