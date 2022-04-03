package happyhouse_team02.land.landservice.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.exception.NoSuchPostException;
import happyhouse_team02.land.landservice.repository.post.PostRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	private final MemberService memberService;
	private final PostRepository postRepository;

	@Override
	public Page<Post> findPostPages(int pageNo, int amount) {
		PageRequest pageRequest = PageRequest.of(pageNo, amount, Sort.by(Sort.Direction.DESC, "createdDate"));
		return postRepository.findAll(pageRequest);
	}

	@Override
	public Post findOne(Long postId) {
		return postRepository.findDistinctById(postId).orElseThrow(NoSuchPostException::new);
	}


	@Override
	@Transactional
	public Long writePost(String loginEmail, PostDto postDto) {
		Member findMember = memberService.findOne(loginEmail);
		Post post = new Post.Builder().member(findMember)
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.build();
		return postRepository.save(post).getId();
	}

	@Override
	@Transactional
	public void updatePost(String loginEmail, PostDto postDto) {
		Post post = findOne(postDto.getId());
		post.confirmAuthority(loginEmail);
		post.updatePost(postDto.getTitle(), postDto.getContent());
	}

	@Override
	@Transactional
	public void deletePost(String loginEmail, Long postId) {
		Post post = findOne(postId);
		post.confirmAuthority(loginEmail);
		postRepository.delete(post);
	}
}
