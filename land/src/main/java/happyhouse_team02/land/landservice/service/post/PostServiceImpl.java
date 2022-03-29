package happyhouse_team02.land.landservice.service.post;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.domain.Role;
import happyhouse_team02.land.landservice.repository.comment.CommentRepository;
import happyhouse_team02.land.landservice.repository.member.MemberValidatedRepository;
import happyhouse_team02.land.landservice.repository.post.PostRepository;
import happyhouse_team02.land.landservice.repository.post.PostValidatedRepository;
import happyhouse_team02.land.landservice.service.comment.CommentDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	private final MemberValidatedRepository memberRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final PostValidatedRepository postValidatedRepository;
	// TODO 리팩터링 해야하는 부분, JPA 를 공부하고 다시 해보자.

	@Override
	public List<PostSummaryDto> findPostsSummary(int pageNo, int amount) {
		return postRepository.findAll(pageNo, amount).stream().map(PostSummaryDto::new).collect(toList());
	}

	@Override
	public Long countPosts() {
		return postRepository.countPosts();
	}

	@Override
	public PostDetailDto findOne(String email, Long postId) {
		Post post = postValidatedRepository.getPost(postId);
		List<Comment> comments = commentRepository.findByPostId(postId);
		PostDetailDto postDetailDto = createPostDetailDto(post, comments);
		addRole(email, postDetailDto);
		return postDetailDto;
	}

	private PostDetailDto createPostDetailDto(Post post, List<Comment> comments) {
		PostDetailDto postDetailDto = new PostDetailDto(post);
		List<CommentDto> commentDto = comments.stream().map(CommentDto::new).collect(toList());
		postDetailDto.setComments(commentDto);
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
		Member findMember = memberRepository.getMember(loginEmail);
		Post post = new Post.Builder().member(findMember)
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.build();
		return postRepository.save(post);
	}
}
