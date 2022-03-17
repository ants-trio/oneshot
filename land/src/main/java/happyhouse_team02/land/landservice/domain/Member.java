package happyhouse_team02.land.landservice.domain;

import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

	@OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
	private final List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Bookmark> bookmarks = new ArrayList<>();

	@Id
	@GeneratedValue()
	private Long id;

	@Embedded
	private Name name;

	@Email
	private String email;
	private String password;

	private Member(Builder builder) {
		name = builder.name;
		email = builder.email;
		password = builder.password;
	}

	public void updateName(Name name, String password) {
		validatePassword(password);
		this.name = name;
	}

	public void updatePassword(String updatePassword, String originalPassword) {
		validatePassword(originalPassword);
		this.password = updatePassword;
	}

	public boolean validatePassword(String password) {
		return getPassword().equals(password);
	}

	@Override
	public String toString() {
		return "Member{" + "name='" + this.getEmail() + '\'' + '}';
	}

	public static class Builder {
		private Name name;
		private String email;
		private String password;

		public Builder name(Name name){
			this.name = name;
			return this;
		}

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
