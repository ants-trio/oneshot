package happyhouse_team02.land.landservice.domain;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Comment extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "COMMNET_ID")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "POST_ID")
	private Post post;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Column(name = "COMMENT_CONTENT")
	private String content;

	private Comment(Member member, Post post) {
		this.member = member;
		this.post = post;
		post.getComments().add(this);
	}

	public static Comment createComment(Member member, Post post){
		return new Comment(member, post);
	}
}