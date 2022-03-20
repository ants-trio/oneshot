package happyhouse_team02.land.landservice.service;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostSummaryDTO {

	private Long id;
	private String title;
	private String writer;
	private LocalDateTime createdDate;

}
