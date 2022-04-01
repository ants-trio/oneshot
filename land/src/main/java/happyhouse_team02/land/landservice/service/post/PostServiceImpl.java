package happyhouse_team02.land.landservice.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.domain.Role;
import happyhouse_team02.land.landservice.exception.NoSuchPostException;
import happyhouse_team02.land.landservice.repository.post.PostJpaRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	private final MemberService memberService;
	private final PostJpaRepository postJpaRepository;

	@Override
	public Page<PostSummaryDto> findPostsSummary(int pageNo, int amount) {
		PageRequest pageRequest = PageRequest.of(pageNo, amount, Sort.by(Sort.Direction.DESC, "createdDate"));
		return postJpaRepository.findAll(pageRequest).map(PostSummaryDto::new);
	}

	@Override
	public PostDetailDto findOne(String email, Long postId) {
		Post post = postJpaRepository.findById(postId).orElseThrow(NoSuchPostException::new);

		return createPostDetailDto(email, post);
	}

	private PostDetailDto createPostDetailDto(String email, Post post) {
		PostDetailDto postDetailDto = new PostDetailDto(post);
		addRole(email, postDetailDto);
		return postDetailDto;
	}

	private void addRole(String email, PostDetailDto postDetailDto) {
		if (postDetailDto.getWriter().equals(email)){
			postDetailDto.setRole(Role.WRITER);
		}
		postDetailDto.getComments().stream()
			.filter(commentDto -> commentDto.getWriter().equals(email))
			.forEach(commentDto -> commentDto.setRole(Role.WRITER));
	}

	@Override
	@Transactional
	public Long writePost(String loginEmail, PostDto postDto) {
		Member findMember = memberService.findOne(loginEmail);
		Post post = new Post.Builder().member(findMember)
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.build();
		return postJpaRepository.save(post).getId();
	}
}
