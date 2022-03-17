
package happyhouse_team02.land.landservice.domain;

import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Apartment {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
}
