package happyhouse_team02.land.landservice.service.comment;

import static happyhouse_team02.land.landservice.domain.CommentRole.*;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.exception.NoSuchCommentException;
import happyhouse_team02.land.landservice.repository.CommentRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import happyhouse_team02.land.landservice.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final MemberService memberService;
	private final PostService postService;

	@Override
	public Comment findOne(Long commentId) {
		return commentRepository.findById(commentId).orElseThrow(NoSuchCommentException::new);
	}

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
	@Transactional
	public Long addComment(String email, CommentDto commentDto) {
		Member findMember = memberService.findOne(email);
		Comment findComment = findOne(commentDto.getCommentId());
		Comment comment = new Comment.Builder().member(findMember)
			.post(findComment.getPost())
			.content(commentDto.getContent())
			.role(CHILDREN)
			.build();
		log.info("comment={}", comment.getId());
		log.info("findComment={}", findComment.getId());
		commentRepository.save(comment);
		log.info("comment={}", comment.getId());

		findComment.addComment(comment);
		return comment.getId();
	}

	@Override
	public List<Comment> findComments(Long postId) {
		return commentRepository.findByPostIdOrderByCreatedDate(postId);
	}

	@Override
	@Transactional
	public void updateComment(String email, CommentDto commentDto) {
		Comment comment = findOne(commentDto.getCommentId());
		comment.isPossibleUpdate(email);
		comment.updateComment(commentDto.getContent());
	}

	@Override
	@Transactional
	public void deleteComment(String email, Long commentId) {
		Comment comment = findOne(commentId);
		if (comment.isPossibleDelete(email)){
			commentRepository.delete(comment);
			return;
		}
		comment.updateComment("삭제된 댓글입니다.");
	}

}
