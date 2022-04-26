package happyhouse_team02.land.landservice.domain;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import happyhouse_team02.land.landservice.exception.UnauthorizedAccessException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseEntity {

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Comment> comments = new ArrayList<>();

	@Id
	@GeneratedValue
	@Column(name = "POST_ID")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Column(name = "POST_TITLE")
	private String title;

	@Lob
	@Column(name = "POST_CONTENT")
	private String content;

	@Column(name = "POST_LIKE")
	private int postLike;

	private Post(Builder builder) {
		member = builder.member;
		title = builder.title;
		content = builder.content;
		member.getPosts().add(this);
	}

	public void updatePost(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void confirmAuthority(String email) {
		if (!member.getEmail().equals(email)) {
			throw new UnauthorizedAccessException();
		}
	}

	public boolean toggleLike() {
		return getPostLike() != getPostLike();
	}

	public static class Builder {
		private Member member;
		private String title;
		private String content;

		public Builder member(Member member) {
			this.member = member;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Post build() {
			return new Post(this);
		}
	}
}
