package happyhouse_team02.land.landservice.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.repository.MemberRepository;
import happyhouse_team02.land.landservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final MemberRepository memberRepository;
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

	@Override
	public Long writePost(String loginEmail, PostDto postDto) {
		Member findMember = getMember(loginEmail);
		Post post = new Post.Builder()
			.member(findMember)
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.build();
		return postRepository.save(post);
	}

	private Member getMember(String loginEmail) {
		return memberRepository.findByEmail(loginEmail).orElseThrow(NoSuchMemberException::new);
	}

}
