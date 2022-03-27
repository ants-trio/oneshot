package happyhouse_team02.land.landservice.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import happyhouse_team02.land.landservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final PostRepository postRepository;

	@Override
	public List<PostSummaryDto> findPostsSummary(int pageNo, int amount) {
		return postRepository.findAll(pageNo, amount)
			.stream()
			.map(PostSummaryDto::new)
			.collect(toList());
	}

	@Override
	public Long countPosts() {
		return postRepository.countPosts();
	}

	@Override
	public PostDto findOne() {
		return null;
	}


}
