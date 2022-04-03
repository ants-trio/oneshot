package happyhouse_team02.land.landservice.domain;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import happyhouse_team02.land.landservice.exception.UnauthorizedAccessException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Comment extends BaseEntity {

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

	private Comment(Builder builder) {
		member = builder.member;
		post = builder.post;
		content = builder.content;
		post.getComments().add(this);
	}

	public void confirmAuthority(String email) {
		if (!member.getEmail().equals(email)) {
			throw new UnauthorizedAccessException();
		}
	}

	public void updateComment(String content) {
		this.content = content;
	}

	public static class Builder {
		private Member member;
		private Post post;
		private String content;

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

		public Comment build() {
			return new Comment(this);
		}
	}
}