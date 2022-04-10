package happyhouse_team02.land.landservice.web.util.strategy;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidatorContext {

	private ValidatorStrategy validatorStrategy;

	private ValidatorContext() {
	}

	public ValidatorContext(ValidatorStrategy validatorStrategy) {
		this.validatorStrategy = validatorStrategy;
	}

	public void validate(Object form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return;
		}
		validatorStrategy.call(form, bindingResult);
	}

}
