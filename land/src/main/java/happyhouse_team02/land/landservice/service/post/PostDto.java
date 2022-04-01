package happyhouse_team02.land.landservice.service.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {

	private Long id;

	private String title;
	private String content;

	public PostDto(String title, String content){
		this.title = title;
		this.content = content;
	}
}
