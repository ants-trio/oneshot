package happyhouse_team02.land.landservice.web.util.strategy;

import org.springframework.validation.BindingResult;

public interface ValidatorStrategy <T>{
	void call(T form, BindingResult bindingResult);
}
