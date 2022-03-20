package happyhouse_team02.land.landservice.service;

import java.util.List;

public interface PostService {

	List<PostSummaryDTO> findPostsSummary();

	PostDto findOne();

}
