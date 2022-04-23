package happyhouse_team02.land.landservice.web.util.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidatorContext {

	private final Map<String, ValidatorStrategy> validatorStrategy;

	public void validate(Object form, BindingResult bindingResult, String strategy) {
		if (bindingResult.hasErrors()) {
			return;
		}
		validatorStrategy.get(strategy).call(form, bindingResult);
	}

}
