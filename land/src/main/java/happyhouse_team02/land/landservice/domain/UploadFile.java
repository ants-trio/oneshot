package happyhouse_team02.land.landservice.domain;

import static lombok.AccessLevel.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class UploadFile {

	private String uploadFileName;
	private String storeFileName;
}
