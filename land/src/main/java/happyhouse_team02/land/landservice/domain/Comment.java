package happyhouse_team02.land.landservice.domain;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {

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

	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime lastUpdatedDate;

	public Comment(Post post, Member member) {
		this.member = member;
		this.post = post;
		post.getComments().add(this);
	}
}