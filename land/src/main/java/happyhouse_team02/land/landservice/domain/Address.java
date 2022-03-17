package happyhouse_team02.land.landservice.domain;

import static lombok.AccessLevel.*;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Address {
	private String street;
}
