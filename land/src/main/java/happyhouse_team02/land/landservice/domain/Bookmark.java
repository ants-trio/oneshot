package happyhouse_team02.land.landservice.domain;

import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Bookmark extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "BOOKMARK_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Embedded
	private Area area;

	public Bookmark(Member member, Area area) {
		this.member = member;
		this.area = area;
		member.getBookmarks().add(this);
	}
}