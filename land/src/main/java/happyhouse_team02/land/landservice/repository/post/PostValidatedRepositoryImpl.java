package happyhouse_team02.land.landservice.repository.post;

import org.springframework.stereotype.Component;

import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.exception.NoSuchPostException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostValidatedRepositoryImpl implements PostValidatedRepository{

	PostRepository postRepository;

	@Override
	public Post getPost(Long postId) {
		return postRepository.findById(postId).orElseThrow(NoSuchPostException::new);
	}
}
