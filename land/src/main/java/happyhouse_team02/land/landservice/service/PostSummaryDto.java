package happyhouse_team02.land.landservice.service;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostSummaryDto {

	private Long id;
	private String title;
	private String writer;
	private LocalDateTime createdDate;

}
