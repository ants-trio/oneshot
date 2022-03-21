package happyhouse_team02.land.landservice.domain;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

	@Column(name = "MEMBER_POSTS")
	@OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
	private final List<Post> posts = new ArrayList<>();

	@Column(name = "MEMBER_BOOKMARKS")
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Bookmark> bookmarks = new ArrayList<>();

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "MEMBER_EMAIL")
	private String email;

	@Column(name = "MEMBER_PASSWORD")
	private String password;

	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime lastUpdatedDate;

	private Member(Builder builder) {
		email = builder.email;
		password = builder.password;
	}

	public void updatePassword(String updatePassword, String originalPassword) {
		isValidatePassword(originalPassword);
		this.password = updatePassword;
	}

	public boolean isValidatePassword(String password) {
		return getPassword().equals(password);
	}

	public void deleteBookmark(Bookmark bookmark){
		this.bookmarks.remove(bookmark);
	}

	@Override
	public String toString() {
		return "Member{" + "name='" + this.getEmail() + '\'' + '}';
	}

	public static class Builder {
		private String email;
		private String password;

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Member build() {
			return new Member(this);
		}
	}
}
