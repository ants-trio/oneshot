package happyhouse_team02.land.landservice.domain;

import static happyhouse_team02.land.landservice.domain.CommentRole.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import happyhouse_team02.land.landservice.exception.UnauthorizedAccessException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Comment extends BaseEntity {

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Comment parent;

	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private final List<Comment> children = new ArrayList<>();

	@Id
	@GeneratedValue
	@Column(name = "COMMENT_ID")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "POST_ID")
	private Post post;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Column(name = "COMMENT_CONTENT")
	private String content;

	@Enumerated(value = EnumType.STRING)
	private CommentRole role;

	private Comment(Builder builder) {
		member = builder.member;
		post = builder.post;
		content = builder.content;
		role = builder.role;
	}

	public void addComment(Comment comment) {
		if (role == CHILDREN) {
			throw new IllegalStateException();
		}
		children.add(comment);
		comment.parent = this;
	}

	public void isPossibleUpdate(String email) {
		if (!member.getEmail().equals(email)) {
			throw new UnauthorizedAccessException();
		}
	}

	public void updateComment(String content) {
		this.content = content;
	}

	public boolean isPossibleDelete(String email) {
		isPossibleUpdate(email);
		return children.isEmpty();
	}

	public static class Builder {
		private Member member;
		private Post post;
		private String content;
		private CommentRole role;

		public Builder member(Member member) {
			this.member = member;
			return this;
		}

		public Builder post(Post post) {
			this.post = post;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder role(CommentRole role) {
			this.role = role;
			return this;
		}

		public Comment build() {
			return new Comment(this);
		}
	}
}