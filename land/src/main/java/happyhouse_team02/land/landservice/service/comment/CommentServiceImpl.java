package happyhouse_team02.land.landservice.service.comment;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.repository.comment.CommentRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import happyhouse_team02.land.landservice.service.post.PostService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final MemberService memberService;
	private final PostService postService;

	@Override
	@Transactional
	public Long writeComment(String email, CommentDto commentDto) {
		Member findMember = memberService.findOne(email);
		Post findPost = postService.findOne(commentDto.getPostId());
		Comment comment = new Comment.Builder().member(findMember)
			.post(findPost)
			.content(commentDto.getContent())
			.build();

		return commentRepository.save(comment).getId();
	}

	@Override
	public List<Comment> findComments(Long postId) {
		return null;
	}
}
