package happyhouse_team02.land.landservice.service.bookmark;

import javax.validation.constraints.NotEmpty;

import happyhouse_team02.land.landservice.domain.Area;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkDto {

	@NotEmpty
	private Area area;

	public BookmarkDto(String city, String region) {
		this.area = new Area(city, region);
	}
}
