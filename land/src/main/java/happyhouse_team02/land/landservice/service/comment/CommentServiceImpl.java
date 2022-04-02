package happyhouse_team02.land.landservice.service.comment;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import happyhouse_team02.land.landservice.domain.Comment;
import happyhouse_team02.land.landservice.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

	private final CommentRepository commentRepository;

	@Override
	public Long writeComment(Comment comment) {
		return null;
	}

	@Override
	public List<Comment> findComments(Long postId) {
		return null;
	}
}
