package happyhouse_team02.land.landservice.service;

import javax.validation.constraints.NotEmpty;

import happyhouse_team02.land.landservice.domain.Area;
import happyhouse_team02.land.landservice.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkDTO {

	@NotEmpty Area area;

	public BookmarkDTO(String city, String region){
		this.area = new Area(city, region);
	}

	public BookmarkDTO(Bookmark bookmark){
		this.area = bookmark.getArea();
	}
}
