package happyhouse_team02.land.landservice.service.post;

import java.util.List;

import happyhouse_team02.land.landservice.domain.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {

	private Long id;
	private String title;
	private String content;
	private UploadFile detachedFile;
	private List<UploadFile> imageFiles;

	public PostDto(String title, String content) {
		this(null, title, content);
	}

	public PostDto(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
}
