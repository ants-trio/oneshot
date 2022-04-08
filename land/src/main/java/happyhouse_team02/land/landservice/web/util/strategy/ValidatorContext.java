package happyhouse_team02.land.landservice.web.util.strategy;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidatorContext <T>{

	private final ValidatorStrategy<T> validatorStrategy;

	public void validate(T form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return;
		}
		validatorStrategy.call(form, bindingResult);
	}
}
